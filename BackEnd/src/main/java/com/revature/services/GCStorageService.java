package com.revature.services;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

//In order for this service to function an env var needs to be set
public class GCStorageService {
	
	public static void uploadObjectFromMemory(String objectName, String contents) throws IOException {
		
		String projectId = "soy-pillar-356718";
		String bucketName = "soy-pillar-356718-receipts";

		// Removing metadata from contents if present to be able to add it to GCStorage
		if (contents.contains(",")) {
			contents = contents.split(",")[1];
		}

		
	    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
	    BlobId blobId = BlobId.of(bucketName, objectName);
	    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build();
	    byte[] content = Base64.getDecoder().decode(contents);
	    storage.createFrom(blobInfo, new ByteArrayInputStream(content));

	    System.out.println(
	        "Object "
	            + objectName
	            + " uploaded to bucket "
	            + bucketName
	            + " with contents "
	            + contents);
	}

}
