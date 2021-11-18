package com.screener.app.services;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.screener.app.controller.FileController;

import org.slf4j.Logger;
import org.apache.tika.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.web.bind.annotation.CrossOrigin;
@RestController

@CrossOrigin(origins="*",allowedHeaders="*")

public class FileService {
	
	 private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	 
	 @Autowired
		private inotificationService inotificationService;
	 
	@POST
	@Path("/uploadMultipleFiles1")
	//@Logged
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(  @FormDataParam("file") InputStream fileInputStream,
	                                @FormDataParam("file") FormDataContentDisposition fileMetaData, 
	                                @FormDataParam("file") final FormDataBodyPart body,
	                                @FormDataParam("notes_uuid") String notes_uuid, 
	                                @FormDataParam("checkImgSrc") int checkImgSrc)throws Exception
//	                                @FormDataParam("type") String type,
//	                                @FormDataParam("patient_id") String patient_id) throws Exception
	{

		String file = "";
		String UPLOAD_PATH = "C://Users/EHRC/Desktop/UPLOADS";
		
		  String fileName = fileMetaData.getFileName();
		  String[] fileDotCheck = fileName.split("\\."); 
		  
		  //extension detection
		    byte[] documentContentByteArray = IOUtils.toByteArray(fileInputStream);
		    InputStream fileInputStreamCheck = new ByteArrayInputStream(documentContentByteArray);
		   // String mimeType = new TikaUtil().identifyFileTypeUsingDefaultTika(fileInputStreamCheck);
		    
		    boolean validFile = false;
		    file = UPLOAD_PATH +  notes_uuid;
		    
			try {
				int read = 0;
				byte[] bytes = new byte[1024];
				File uploadFile = new File(file);
				OutputStream out = new FileOutputStream(uploadFile);
				logger.info("Reading uploads.");
				while ((read = fileInputStreamCheck.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();

				Set<PosixFilePermission> perms = new HashSet<>();
				perms.add(PosixFilePermission.OWNER_READ);
				perms.add(PosixFilePermission.OWNER_WRITE);
				perms.add(PosixFilePermission.OTHERS_READ);
				perms.add(PosixFilePermission.GROUP_READ);

				Files.setPosixFilePermissions(uploadFile.toPath(), perms);
				

				//Response res = generalUpload(file, fileName,checkImgSrc);
				Response res = generalUpload(file,checkImgSrc);
				return res;

			} catch (IOException e) {
				logger.error("Error while uploading file. Please try again !!! ::", e);
//				ResponseConfig responses = new ResponseConfig();
//				responses.setCode(Response.Status.BAD_REQUEST.getStatusCode());
//				
//				responses.setLocation("upload");
//				responses.setMessage("Unable to upload File. Something went wrong. Please try again.");
//				responses.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
//				return Response.status(Response.Status.BAD_REQUEST).entity(responses)
//						.build();
			}
			return null;
			
			
			
			
			//MHMS FILESERVICE CODE
			
//			String file = "";
//		    String UPLOAD_PATH = LoadConfig.getConfigValue("MHMS_FILE_UPLOAD");
//		    String tokenId = token_id;
//		    
//		    if(!type.matches(PatternUtil.fileNameOnly) || !state.matches(PatternUtil.alphabetOnly) || !patient_id.matches(PatternUtil.uuids) || !token_id.matches(PatternUtil.uuids)) {
//		    	ResponseConfig responses = new ResponseConfig();
//				responses.setCode(Response.Status.BAD_REQUEST.getStatusCode());
//				
//				responses.setLocation("upload");
//				responses.setMessage("Filename and type does not match to the mentioned criteria.");
//				responses.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
//				return Response.status(Response.Status.BAD_REQUEST).entity(responses)
//						.header("Header", "Random response").build();
//		    }
//		    String fileType = type;
//		    String patientId = patient_id;
//		    String fileName = fileMetaData.getFileName();
////		    String mimeType = body.getMediaType().toString();
//		    
//		    String[] fileDotCheck = fileName.split("\\."); 
//		    
//		    
//		    //extension detection
//		    byte[] documentContentByteArray = IOUtils.toByteArray(fileInputStream);
//		    InputStream fileInputStreamCheck = new ByteArrayInputStream(documentContentByteArray);
//		    String mimeType = new TikaUtil().identifyFileTypeUsingDefaultTika(fileInputStreamCheck);
//		    
//		    boolean validFile = false;
//		    
//		    if(fileName.length()>100) {
//		    	fileName = fileName.substring(fileName.length()-100);
//		    }
//		    
//		    boolean filenameOk = Pattern.matches(PatternUtil.fileNameOnly, fileName);
//		    
//		    if(!filenameOk) {
//				ResponseConfig responses = new ResponseConfig();
//				responses.setCode(Response.Status.BAD_REQUEST.getStatusCode());
//				
//				responses.setLocation("upload");
//				responses.setMessage("Filename can contain only alphanumeric - (hyphen) _ (underscore) and spaces only.");
//				responses.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
//				return Response.status(Response.Status.BAD_REQUEST).entity(responses)
//						.header("Header", "Random response").build();
//		    }
//		    
//		    logger.info("File format Check FileCheck Size = "+fileDotCheck.length+" FileCheck Type = "+fileDotCheck[1]);
//		    if(fileDotCheck.length == 2 && (fileDotCheck[1].equalsIgnoreCase("jpg") || fileDotCheck[1].equalsIgnoreCase("jpeg") || fileDotCheck[1].equalsIgnoreCase("png") || fileDotCheck[1].equalsIgnoreCase("pdf"))) {
//				if (fileName.length() != 0 && fileName.length() < 100 && !fileName.contains("\0")) {
//					validFile = true;
//					logger.info("File format Correct");
//
//					if (validFile && (mimeType.equals("image/jpeg") || mimeType.equals("image/png")
//							|| mimeType.equals("application/pdf"))) {
//						logger.info("Uploaded File Type is " + mimeType + ".");
//						if (fileType == "BMR")
//							file = UPLOAD_PATH + "BMR/" + patientId + "_" + System.currentTimeMillis() + "." + fileDotCheck[1];
//						else
//							file = UPLOAD_PATH + state + "/" + tokenId + "_" + System.currentTimeMillis() + "." + fileDotCheck[1];
//
//						try {
//							int read = 0;
//							byte[] bytes = new byte[1024];
//							File uploadFile = new File(file);
//							OutputStream out = new FileOutputStream(uploadFile);
//							logger.info("Reading uploads.");
//							while ((read = fileInputStreamCheck.read(bytes)) != -1) {
//								out.write(bytes, 0, read);
//							}
//							out.flush();
//							out.close();
//
//							Set<PosixFilePermission> perms = new HashSet<>();
//							perms.add(PosixFilePermission.OWNER_READ);
//							perms.add(PosixFilePermission.OWNER_WRITE);
//							perms.add(PosixFilePermission.OTHERS_READ);
//							perms.add(PosixFilePermission.GROUP_READ);
//
//							Files.setPosixFilePermissions(uploadFile.toPath(), perms);
//
//							Response res = generalUpload(file, fileType, fileName);
//							return res;
//
//						} catch (IOException e) {
//							logger.error("Error while uploading file. Please try again !!! ::", e);
//							ResponseConfig responses = new ResponseConfig();
//							responses.setCode(Response.Status.BAD_REQUEST.getStatusCode());
//							
//							responses.setLocation("upload");
//							responses.setMessage("Unable to upload File. Something went wrong. Please try again.");
//							responses.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
//							return Response.status(Response.Status.BAD_REQUEST).entity(responses)
//									.build();
//						}
//					} else {
//						logger.info("File upload not allowed for file format " + mimeType + ".");
//						ResponseConfig responses = new ResponseConfig();
//						responses.setCode(Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode());
//						
//						responses.setLocation("upload");
//						responses.setMessage("File upload not allowed for this file format or seems to be corrupted.");
//						responses.setStatus(Response.Status.UNSUPPORTED_MEDIA_TYPE.getStatusCode());
//						return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE)
//								.header("Header", "Random response for wrong file format for this call.").entity(responses)
//								.build();
//					}
//				} else {
//					logger.info("File corrupted");
//					ResponseConfig responses = new ResponseConfig();
//					responses.setCode(Response.Status.BAD_REQUEST.getStatusCode());
//					
//					responses.setLocation("upload");
//					responses.setMessage("File seems to be corrupted. Please check for the correct file.");
//					responses.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
//					return Response.status(Response.Status.BAD_REQUEST).entity(responses)
//							.header("Header", "Random response for wrong file format").build();
//				}
//		    } else {
//		    	logger.info("File contains multiple extension");
//		    	ResponseConfig responses = new ResponseConfig();
//				responses.setCode(Response.Status.BAD_REQUEST.getStatusCode());
//				
//				responses.setLocation("upload");
//				responses.setMessage("There seems to a problem in filename or extension.");
//				responses.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
//				return Response.status(Response.Status.BAD_REQUEST).entity(responses).header("Header", "Random response for wrong file format").build();
//		    }
//		    
		    
		    
	    
	    
	}
	
	public Response generalUpload(String filePath,int checkImgSrc)
	{
		//com.screener.app.services fileData = storeFile(filePath, fileType, fileName,checkImgSrc);
		inotificationService.updateImageUrl(filePath,checkImgSrc);
		String fileData="";
		if(fileData != null) {
			return Response.status(Response.Status.OK).entity(fileData).build();
		} else {
			System.out.println("Bad request");
//			ResponseConfig responses = new ResponseConfig();
//			responses.setCode(Response.Status.BAD_REQUEST.getStatusCode());
//			
//			responses.setLocation("upload");
//			responses.setMessage("Unable to upload File. Something went wrong. Please try again.");
//			responses.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
//			return Response.status(Response.Status.BAD_REQUEST).entity(responses).build();
			return null;
	}
	}
}
