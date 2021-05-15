package com.teknei.concesionario.service;

import java.util.List;

import com.filenet.api.admin.StorageArea;
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
import com.teknei.concesionario.model.Coche;

public class FileNetServiceImpl implements FileNetService<Coche> {

    @Override
    public ObjectStore getObjectStore() {
        final String URI = "http://34.234.153.200/wsi/FNCEWS40MTOM";
        final String OS_NAME = "GenericObjectStore";
        final Connection connection = Factory.Connection.getConnection(URI);
        final Domain domain = Factory.Domain.getInstance(connection, null);
        return Factory.ObjectStore.fetchInstance(domain, OS_NAME, null);
    }

    @Override
    public void CreateDocument(List<Coche> list, ObjectStore os) {
        final Document doc = Factory.Document.createInstance(os, ClassNames.DOCUMENT);
        // own logic to populate document with the list of cars
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
