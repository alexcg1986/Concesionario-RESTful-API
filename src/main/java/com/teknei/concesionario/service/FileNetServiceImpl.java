package com.teknei.concesionario.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.filenet.api.collection.ContentElementList;
import com.filenet.api.collection.StringList;
import com.filenet.api.constants.AutoClassify;
import com.filenet.api.constants.AutoUniqueName;
import com.filenet.api.constants.CheckinType;
import com.filenet.api.constants.DefineSecurityParentage;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.Connection;
import com.filenet.api.core.ContentTransfer;
import com.filenet.api.core.Document;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.property.Properties;
import com.filenet.api.util.UserContext;
import com.itextpdf.kernel.colors.WebColors;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.teknei.concesionario.config.FileNetConfig;
import com.teknei.concesionario.dto.CocheDTO;
import com.teknei.concesionario.dto.DocumentoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileNetServiceImpl implements FileNetService {

    @Autowired
    FileNetConfig fileNetConfig;

    @Override
    public Connection getConnection() {
        return Factory.Connection.getConnection(fileNetConfig.getUri());
    }

    @Override
    public void pushSubject(Connection connection, String USERNAME, String PASS) {
        UserContext.get()
                .pushSubject(UserContext.createSubject(connection, USERNAME, PASS, fileNetConfig.getJaas_stanza()));
    }

    @Override
    public Iterator<?> getObjectStoreIterator(Connection connection) {
        return Factory.Domain.fetchInstance(connection, null, null).get_ObjectStores().iterator();
    }

    @Override
    public Folder getFolder(ObjectStore objectStore) {
        return Factory.Folder.fetchInstance(objectStore, fileNetConfig.getPath(), null);
    }

    @Override
    public void popSubject() {
        UserContext.get().popSubject();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void generatePDF(List<CocheDTO> list, String USERNAME, String PASS) {
        final Connection connection = getConnection();
        pushSubject(connection, USERNAME, PASS);

        try {
            final Iterator<?> iterator = getObjectStoreIterator(connection);

            while (iterator.hasNext()) {
                final ObjectStore objectStore = (ObjectStore) iterator.next();
                final Document doc = Factory.Document.createInstance(objectStore, fileNetConfig.getDoc_class());

                final Set<String> marcasSet = new HashSet<>();
                final Set<String> modelosSet = new HashSet<>();
                final Set<String> matriculasSet = new HashSet<>();
                list.forEach(cocheDTO -> {
                    marcasSet.add(cocheDTO.getMarca().getNombre());
                    modelosSet.add(cocheDTO.getModelo().getNombre());
                    matriculasSet.add(cocheDTO.getMatricula());
                });

                final StringList marcas = Factory.StringList.createList();
                marcasSet.forEach(marcas::add);
                final StringList modelos = Factory.StringList.createList();
                modelosSet.forEach(modelos::add);
                final StringList matriculas = Factory.StringList.createList();
                matriculasSet.forEach(matriculas::add);

                final String DOCUMENT_TITLE = marcas.size() == 1 ? marcas.get(0).toString().toUpperCase() : "COCHES";
                final String MIME_TYPE = "application/pdf";

                doc.getProperties().putValue("DocumentTitle", DOCUMENT_TITLE);
                doc.getProperties().putValue("Marca", marcas);
                doc.getProperties().putValue("Modelo", modelos);
                doc.getProperties().putValue("Matricula", matriculas);

                final ContentElementList contentElementList = Factory.ContentElement.createList();
                final ContentTransfer contentTransfer = Factory.ContentTransfer.createInstance();

                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                final PdfDocument pdfDocument = new PdfDocument(new PdfWriter(byteArrayOutputStream));
                final com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);

                final float[] pointColumnWidths = { 150F, 150F, 150F };
                final Table table = new Table(pointColumnWidths);
                final Style styleHead = new Style();
                styleHead.setBackgroundColor(WebColors.getRGBColor("cornflowerblue"));
                styleHead.setFontColor(WebColors.getRGBColor("white"));
                table.addCell(new Cell().add(new Paragraph("Marca")).addStyle(styleHead));
                table.addCell(new Cell().add(new Paragraph("Modelo")).addStyle(styleHead));
                table.addCell(new Cell().add(new Paragraph("Matrícula")).addStyle(styleHead));
                for (int i = 0; i < list.size(); ++i) {
                    table.addCell(new Cell().add(new Paragraph(list.get(i).getMarca().getNombre()))
                            .addStyle(i % 2 == 0 ? new Style().setBackgroundColor(WebColors.getRGBColor("white"))
                                    : new Style().setBackgroundColor(WebColors.getRGBColor("lightsteelblue"))));
                    table.addCell(new Cell().add(new Paragraph(list.get(i).getModelo().getNombre()))
                            .addStyle(i % 2 == 0 ? new Style().setBackgroundColor(WebColors.getRGBColor("white"))
                                    : new Style().setBackgroundColor(WebColors.getRGBColor("lightsteelblue"))));
                    table.addCell(new Cell().add(new Paragraph(list.get(i).getMatricula()))
                            .addStyle(i % 2 == 0 ? new Style().setBackgroundColor(WebColors.getRGBColor("white"))
                                    : new Style().setBackgroundColor(WebColors.getRGBColor("lightsteelblue"))));
                }

                document.add(table);
                document.close();

                contentTransfer.setCaptureSource(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                contentTransfer.set_ContentType(MIME_TYPE);
                contentTransfer.set_RetrievalName(DOCUMENT_TITLE);
                contentElementList.add(contentTransfer);

                doc.set_ContentElements(contentElementList);
                doc.set_MimeType(MIME_TYPE);
                doc.checkin(AutoClassify.AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
                doc.save(RefreshMode.REFRESH);

                getFolder(objectStore).file(doc, AutoUniqueName.AUTO_UNIQUE, doc.get_Name(),
                        DefineSecurityParentage.DEFINE_SECURITY_PARENTAGE).save(RefreshMode.REFRESH);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            popSubject();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DocumentoDTO> getDocuments(final String USERNAME, final String PASS) {
        final Connection connection = getConnection();
        pushSubject(connection, USERNAME, PASS);
        final List<DocumentoDTO> documentos = new ArrayList<>();

        try {
            final Iterator<?> objectStoreIterator = getObjectStoreIterator(connection);

            while (objectStoreIterator.hasNext()) {
                final Iterator<?> documentIterator = getFolder((ObjectStore) objectStoreIterator.next())
                        .get_ContainedDocuments().iterator();

                while (documentIterator.hasNext()) {
                    final Document document = ((Document) documentIterator.next());
                    final Properties properties = document.getProperties();
                    final DocumentoDTO documento = new DocumentoDTO();

                    documento.setGuid(document.get_Id().toString());
                    documento.setTitulo(properties.getStringValue("DocumentTitle"));
                    documento.setMarcas((List<String>) properties.getStringListValue("Marca").stream()
                            .collect(Collectors.toList()));
                    documento.setModelos((List<String>) properties.getStringListValue("Modelo").stream()
                            .collect(Collectors.toList()));
                    documento.setMatriculas((List<String>) properties.getStringListValue("Matricula").stream()
                            .collect(Collectors.toList()));
                    documentos.add(documento);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        } finally {
            popSubject();
        }
        return documentos;
    }
}
