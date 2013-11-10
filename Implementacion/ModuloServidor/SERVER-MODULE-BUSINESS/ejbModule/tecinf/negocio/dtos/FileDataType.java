package tecinf.negocio.dtos;

import java.io.File;
import java.io.Serializable;

public class FileDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private File file;
	private String fileName;
	private String mimeType;
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}
