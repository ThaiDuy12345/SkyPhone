package com.nhathanh.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nhathanh.dao.DienThoaiDAO;
import com.nhathanh.model.DienThoai;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageDienThoaiController {
	@Autowired
	DienThoaiDAO dao;
	@Autowired
	ServletContext app;
	String srcPhone = "src\\main\\resources\\static\\images\\phone_images\\";
	String message="";

	// Controller xử lý hình ảnh của sản phẩm
	@RequestMapping("skyPhone/image")
	public String dienThoaiImg(Model model) {
		model.addAttribute("sanPham", getProduct());
		model.addAttribute("soLuongAnh", kiemTraSoLuongAnh(dao.findAll()));
		if(!message.equals("")) {
			model.addAttribute("message", message);
			message="";
		}
		return "pageAdmin/updateImage";
	}

	@RequestMapping("/skyPhone/image/{id}")
	public String linkImage(@PathVariable("id") String idImage, Model model) {
		DienThoai dt = dao.findById(idImage).get();
		model.addAttribute("dienThoai", dt);
		String path = srcPhone + dt.getId_dt() + "/";
		model.addAttribute("mainImage",
				new File(path + "0.jpg").exists() ? "/images/phone_images/" + dt.getId_dt() + "/0.jpg" : "#");
		model.addAttribute("image1",
				new File(path + "1.jpg").exists() ? "/images/phone_images/" + dt.getId_dt() + "/1.jpg" : "#");
		model.addAttribute("image2",
				new File(path + "2.jpg").exists() ? "/images/phone_images/" + dt.getId_dt() + "/2.jpg" : "#");
		model.addAttribute("image3",
				new File(path + "3.jpg").exists() ? "/images/phone_images/" + dt.getId_dt() + "/3.jpg" : "#");
		if(!message.equals("")) {
			model.addAttribute("message", message);
			message="";
		}
		return "forward:/skyPhone/image";
	}

	public List<Integer> kiemTraSoLuongAnh(List<DienThoai> list) {
		List<Integer> listCount = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			int count = 0;
			String path = srcPhone + list.get(i).getId_dt() + "/";
			File anhChinh = new File(path + "0.jpg");
			File anhPhu1 = new File(path + "1.jpg");
			File anhPhu2 = new File(path + "2.jpg");
			File anhPhu3 = new File(path + "3.jpg");
			count = anhChinh.exists() ? count + 1 : count;
			count = anhPhu1.exists() ? count + 1 : count;
			count = anhPhu2.exists() ? count + 1 : count;
			count = anhPhu3.exists() ? count + 1 : count;
			listCount.add(count);
		}
		return listCount;
	}

	@PostMapping("/skyPhone/image/add/{id}")
	public String capNhatAnh(@PathVariable("id") String id, @RequestParam("anhChinh") MultipartFile mainImage,
			@RequestParam("anhPhu1") MultipartFile image1, @RequestParam("anhPhu2") MultipartFile image2,
			@RequestParam("anhPhu3") MultipartFile image3, Model model) throws InterruptedException {
		String path = app.getRealPath("/images/phone_images/");
		File filePath = new File(path + id + "/");
		if (!filePath.exists()) { 
			filePath.mkdirs();
		}
		for (int i = 0; i < 4; i++) {
			try {
				File file = new File(filePath, i + ".jpg");
				if (i == 0) {
					saveImage(mainImage, file.getName(), id);
				} else if (i == 1) {
					saveImage(image1, file.getName(), id);
				} else if (i == 2) {
					saveImage(image2, file.getName(), id);
				} else {
					saveImage(image3, file.getName(), id);
				}
				message="Bạn vừa cập nhật ảnh SkyPhone thành công!";
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "redirect:/skyPhone/image/" + id;
	}

	List<DienThoai> getProduct() {
		// Lấy ra toàn bộ sản phẩm trong SkyPhone
		List<DienThoai> ds = dao.findAll();
		// Kiểm tra xem thư mục này đã tồn tại hay chưa
		for (int i = 0; i < ds.size(); i++) {
			// Lấy ra id của điện thoại xem thử thư mục có tồn tại hay chưa
			// Nếu chưa thì sản phẩm đó sẽ được xét vào danh sách sản phẩm chưa có hình
			File f = new File(srcPhone + ds.get(i).getId_dt());
			if (f.exists()) {
				ds.remove(i);
			}
		}
		// Lưu số lượng ảnh có trong thư mục của các sản phẩm có sẵn nè
		return ds;
	}

	// Nếu thư mục images_phone chưa được tạo thì sẽ tạo
	public void createFloder() {
		File uploadRootDir = new File(srcPhone);
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
	}

	public String saveImage(MultipartFile attach, String filename, String id) throws IOException {
		// Lấy đường dẫn chính của thư mục upload
		File uploadRootDir = new File(srcPhone);
		// Nếu thư mục chưa được tạo thì sẽ tạo
		createFloder();
		//Tạo thư mục chứa id sản phẩm nếu chưa được tạo
		File hihi = new File(uploadRootDir.getAbsoluteFile() + "/" + id);
		if (!hihi.exists()) {
			hihi.mkdirs();
		}
		//Lưu file 
		File serverFile = new File(uploadRootDir.getAbsoluteFile() + "/" + id + "/" + filename);
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		stream.write(attach.getBytes());
		stream.close();
		return filename;
	}
}
