package com.nhathanh.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {
	@Autowired
	HttpServletRequest request;
	@Autowired
	ServletContext app;

	/**
	 * Đọc chuỗi giá trị của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public String getString(String name, String defaultValue) {
		String value = request.getParameter(name);
		if (value != null) {
			return value;
		} else {
			return defaultValue;
		}
	}

	/**
	 * Đọc số nguyên giá trị của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public int getInt(String name, int defaultValue) {
		String value = request.getParameter(name);
		try {
			int valueInt = Integer.parseInt(value);
			if (value != null) {
				return valueInt;
			} else {
				return defaultValue;
			}
		} catch (NumberFormatException e) {
			System.out.println("Lỗi khi chuyển đổi kiểu dữ liệu !");
			return 0;
		}

	}

	/**
	 * Đọc số thực giá trị của tham số
	 * 
	 * @param name         tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public double getDouble(String name, double defaultValue) {
		String value = request.getParameter(name);
		try {
			double valueInt = Double.parseDouble(value);
			if (value != null) {
				return valueInt;
			} else {
				return defaultValue;
			}
		} catch (NumberFormatException e) {
			System.out.println("Lỗi khi chuyển đổi kiểu dữ liệu !");
			return 0;
		}
	}

	/**
	 * Đọc giá trị boolean của tham số
	 * 
	 * @param name tên tham số
	 * @param defaultValue giá trị mặc định
	 * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	 */
	public boolean getBoolean(String name, boolean defaultValue) {
		String value = request.getParameter(name);
		try {
			boolean valueInt = Boolean.parseBoolean(value);
			if (value != null) {
				return valueInt;
			} else {
				return defaultValue;
			}
		} catch (NumberFormatException e) {
			System.out.println("Lỗi khi chuyển đổi kiểu dữ liệu !");
			return false;
		}
	}

	/**
	 * Đọc giá trị thời gian của tham số
	 * 
	 * @param name    tên tham số
	 * @param pattern là định dạng thời gian
	 * @return giá trị tham số hoặc null nếu không tồn tại
	 * @throws RuntimeException lỗi sai định dạng
	 */
	// pattern="\d{1,2}/\d{1,2}/\d{4}"
	public Date getDate(String name, String pattern) throws RuntimeException {
		String value = request.getParameter(name);
		try {
			if (!value.matches(pattern)) {
				return Date.valueOf(value);
			}
		} catch (Exception e) {
			System.out.println("Lỗi gì đó không biết nữa !" + e);
		}
		return null;
	}

//	/**
//	 * Lưu file upload vào thư mục
//	 * 
//	 * @param file chứa file upload từ client
//	 * @param path đường dẫn tính từ webroot
//	 * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
//	 * @throws RuntimeException lỗi lưu file
//	 */
//	public File save(MultipartFile file, String path) {
//		File dir = new File(app.getRealPath(path));
//		// Khởi tạo thư mục nếu thư mục chưa được tạo
//		if (!dir.exists()) {
//			dir.mkdirs();
//		}
//		try {
//			File saveFile = new File(dir, file.getOriginalFilename());
//			file.transferTo(saveFile);
//			System.out.println(((MultipartFile) saveFile).getOriginalFilename());
//			return saveFile;
//		} catch (Exception e) {
//			System.out.println(" Lỗi gì dậy ta ưi!" + e);
//			throw new RuntimeException(e);
//		}
//	}
	// Nếu thư mục upload chưa được tạo thì sẽ tạo
	public File createFloder(String nameFolder) {
		File uploadRootDir = new File(app.getRealPath(nameFolder));
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		return uploadRootDir;
	}
	
	// Lưu hình người dùng đã chọn
		public String saveImage(MultipartFile attach,String path) throws IOException {
			// Nếu thư mục chưa được tạo thì sẽ tạo
			File uploadRootDir= createFloder(path);
			// Lấy tên file được chọn
			String filename = attach.getOriginalFilename();
			File serverFile = new File(uploadRootDir.getAbsoluteFile() + File.separator + filename);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(attach.getBytes());
			stream.close();
			return filename;
		}

}
