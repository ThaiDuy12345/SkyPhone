package com.nhathanh.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nhathanh.dao.TaiKhoanDAO;
import com.nhathanh.model.TaiKhoan;

@Controller
public class RegisController {
	@Autowired
	TaiKhoanDAO tkDao;
	@Autowired
	ServletContext app;

	@RequestMapping("/pageAccount")
	public String loadData() {
		return "pageAccount/regis";
	}

	@RequestMapping("/pageAccount/regis")
	public String Regis(Model model,@RequestParam("cmnd")String cmnd, @ModelAttribute("taiKhoan")TaiKhoan tkhoan ,@RequestParam("image") MultipartFile attach) throws IOException {
		TaiKhoan tk = new TaiKhoan();
		String image = "";
		String error;
		createFloder();
		tk.setVai_tro(1);
		tk.setCmnd(cmnd);
		if (attach.isEmpty()) {
			error = "Vui lòng chọn ảnh đại diện của bạn!";
			model.addAttribute("message", error);
			model.addAttribute("errorImage", "");
			try {
				image = saveImage(attach);
			} catch (IOException e) {
				e.printStackTrace();
			}
			tk.setHinh(image);
		}
		model.addAttribute("message","Đăng ký thành viên thành công!");
		tkDao.save(tk);
		return "pageAccount/login";
	}

	// Nếu thư mục upload chưa được tạo thì sẽ tạo
	public void createFloder() {
		File uploadRootDir = new File(app.getRealPath("upload"));
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
	}

	// Lưu hình người dùng đã chọn
	public String saveImage(MultipartFile attach) throws IOException {
		// Lấy đường dẫn chính của thư mục upload
		File uploadRootDir = new File(app.getRealPath("upload"));
		// Nếu thư mục chưa được tạo thì sẽ tạo
		createFloder();
		// Lấy tên file được chọn
		String filename = attach.getOriginalFilename();
		File serverFile = new File(uploadRootDir.getAbsoluteFile() + File.separator + filename);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(attach.getBytes());
		stream.close();
		return filename;
	}
}
