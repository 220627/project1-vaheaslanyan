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
//	public static void uploadObject(String objectName, String filePath) throws IOException {
//		
//			String projectId = "soy-pillar-356718";
//			String bucketName = "soy-pillar-356718-receipts";		    
//
//		    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
//		    BlobId blobId = BlobId.of(bucketName, objectName);
//		    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
//		    storage.create(blobInfo, Files.readAllBytes(Paths.get(filePath)));
//
//		    System.out.println(
//		        "File " + filePath + " uploaded to bucket " + bucketName + " as " + objectName);
//		  }
	
	public static void uploadObjectFromMemory(String objectName, String contents) throws IOException {
		String projectId = "soy-pillar-356718";
		String bucketName = "soy-pillar-356718-receipts";
			// The ID of your GCP project
		    // String projectId = "your-project-id";
		if (contents.contains(",")) {
			contents = contents.split(",")[1];
		}
		    // The ID of your GCS bucket
		    // String bucketName = "your-unique-bucket-name";

		    // The ID of your GCS object
		    // String objectName = "your-object-name";

		    // The string of contents you wish to upload
		    // String contents = "Hello world!";

		    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		    BlobId blobId = BlobId.of(bucketName, objectName);
		    BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/jpeg").build(); //.newBuilder(blobId).build();
		    byte[] content = Base64.getDecoder().decode(contents); //getDecoder().decode(contents.getBytes()); //contents.getBytes(StandardCharsets.UTF_8);
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
