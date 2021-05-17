package com.teknei.concesionario.service;

import java.util.Iterator;
import java.util.List;

import javax.security.auth.Subject;

import com.filenet.api.admin.StorageArea;
import com.filenet.api.collection.ObjectStoreSet;
import com.filenet.api.constants.AutoClassify;
import com.filenet.api.constants.AutoUniqueName;
import com.filenet.api.constants.CheckinType;
import com.filenet.api.constants.ClassNames;
import com.filenet.api.constants.DefineSecurityParentage;
import com.filenet.api.constants.RefreshMode;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Document;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.Folder;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.core.ReferentialContainmentRelationship;
import com.filenet.api.util.Id;
import com.filenet.api.util.UserContext;
import com.teknei.concesionario.dto.CocheDTO;

import org.springframework.stereotype.Service;

@Service
public class FileNetServiceImpl implements FileNetService<CocheDTO> {

    @Override
    public void getObjectStore() {
        final String URI = "http://34.234.153.200/wsi/FNCEWS40MTOM";
        final String USERNAME = "Alejandro";
        final String PASS = "Hola1234$";
        final Connection connection = Factory.Connection.getConnection(URI);
        UserContext usercontext = UserContext.get();
        Subject subject = UserContext.createSubject(connection, USERNAME, PASS, "FileNetP8WSI");
        usercontext.pushSubject(subject);

        try {
            final Domain domain = Factory.Domain.getInstance(connection, null);
            final ObjectStoreSet objectStoreSet = domain.get_ObjectStores();
            final Iterator<?> iterator = objectStoreSet.iterator();
            while (iterator.hasNext()) {
                final ObjectStore objStore = (ObjectStore) iterator.next();
                final String OBJ_STORE_NAME = objStore.get_DisplayName();
                System.out.println("Object store name = " + OBJ_STORE_NAME);
            }
        } finally {
            usercontext.popSubject();
        }
    }

    @Override
    public void CreateDocument(List<CocheDTO> list, ObjectStore os) {
        final Document doc = Factory.Document.createInstance(os, ClassNames.DOCUMENT);
        // TO-DO: implement logic to populate document with the list of cars
        doc.getProperties().putValue("Modelos", "New Document via Java API");
        doc.set_MimeType("application/pdf");
        final StorageArea sa = Factory.StorageArea.getInstance(os, new Id("{DE42374D-B04B-4F47-A62E-CAC9AC9A5719}"));
        doc.set_StorageArea(sa);
        doc.save(RefreshMode.NO_REFRESH);
        doc.checkin(AutoClassify.DO_NOT_AUTO_CLASSIFY, CheckinType.MAJOR_VERSION);
        doc.save(RefreshMode.NO_REFRESH);
        final Folder folder = Factory.Folder.getInstance(os, ClassNames.FOLDER,
                new Id("{42A3FC29-D635-4C37-8C86-84BAC73FFA3F}"));
        final ReferentialContainmentRelationship rcr = folder.file(doc, AutoUniqueName.AUTO_UNIQUE,
                "New Document via Java API", DefineSecurityParentage.DO_NOT_DEFINE_SECURITY_PARENTAGE);
        rcr.save(RefreshMode.NO_REFRESH);
    }
}
