package com.nhathanh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhathanh.dao.TaiKhoanDAO;
import com.nhathanh.model.TaiKhoan;
import com.nhathanh.service.MailerService;
import com.nhathanh.service.SessionService;

@Controller
public class AuthController {
	@Autowired
	TaiKhoanDAO daoTk;
	@Autowired
	SessionService session;
	@Autowired
	MailerService mailer;


//	// Đăng nhập
//	@RequestMapping("/admin/login")
//	public String login(Model model, @RequestParam("username") String username,
//			@RequestParam("password") String password) {
//		try {
//			TaiKhoan user = daoTk.getOne(username);
//			if (!user.getPassword().equals(password)) {
//				model.addAttribute("message", "Mật khẩu không hợp lệ thử lại !");
//			} else {
//				session.set("user", user);
//				String uri = (String) session.get("security-uri");
//				if (uri != null) {
//					return "redirect:" + uri;
//				} else {
//					model.addAttribute("message", "Đăng nhập thành công !");
//					return "pageAccount/login";
//				}
//			}
//		} catch (Exception e) {
//			model.addAttribute("message", "Email không tồn tại!");
//		}
//		return ""; 
//	}
//	//Đăng xuất
//	@RequestMapping("/skyPhone/logout")
//	public String logout(Model model) {
//		session.set("user", "");
//		model.addAttribute("message", "Đăng xuất thành công!");
//		return "redirect:/login";
//	}

	// Reset lại password
	@RequestMapping("/admin/resetPass")
	public String resetPass(@RequestParam("emailForget") String email, Model model) {
		try {
			TaiKhoan user = daoTk.getOne(email);
			if (user.getEmail().equals(email)) {
				// cập nhật lại pass và Send mail thông báo
				updatPass(user);
				sendMail(email, "Hệ thống SkyPhone thông báo", body(user.getHo_ten()));
				model.addAttribute("message", "Tài khoản của bạn đã được reset mật khẩu, vui lòng kiểm tra mail!");
			} else {
				model.addAttribute("message", "Không tìm thấy tài khoản của bạn!");
			}
		} catch (Exception e) {
			model.addAttribute("message", "Email không tồn tại!");
		}
		return "pageAccount/login";
	}
	//Gửi maill thông báod dến người dùng
	void sendMail(String to, String subject, String body) {
		try {
			mailer.queue(to, subject, body);
			System.out.println("Gửi mail thành công");
		} catch (Exception e) {
			System.out.println("Gửi mail thất bại");
		}
	}
	//Hàm dùng Reset lại password
	void updatPass(TaiKhoan user) {
		user.setPassword("123@123");
		daoTk.save(user);
	}

	String body(String name) {
		String body = "  <table bgcolor=\"\" style=\"font-family:'Helvetica','Arial',san-serif;margin:0;max-width:100%;padding:0;width:100%\">\r\n"
				+ "        <tbody>\r\n"
				+ "            <tr>\r\n"
				+ "                <td>\r\n"
				+ "                    <center>&nbsp;\r\n"
				+ "                        <table style=\"margin:10px auto;max-width:100%;width:600\">\r\n"
				+ "                            <tbody>\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td width=\"600\" align=\"right\" style=\"font-size:12px;font-style:italic;padding-right:50px;text-align:right\">\r\n"
				+ "                                        <a href=\"\" target=\"_blank\" data-saferedirecturl=\"\">\r\n"
				+ "                                            <font color=\"#aaaaaa\">Xem bằng trình duyệt →</font>\r\n"
				+ "                                        </a>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td style=\"text-align:center\">\r\n"
				+ "                                        <center>\r\n"
				+ "                                            <a href=\"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://i.trellomail.com/a/click?_t%3Dcce9a87967214196ae72b08c7283fda9%26_m%3Dd82e0b9ebebb45b7ac464b16b74c73eb%26_e%3DnKt1uyMrDz_riD8Kd73sWTqGHXhhM0un2KAYAbgvL7T9P63W7Ek8N74h_YiKlDIK2peUTJQVk-0iMclFMbPGWFLuSxGJkr1LqXnw25fZFkzE_hF90JSun50tsEu5lXjrBE6t2Cymd7QzlXVH_5QaWtUEU6qWxoPloX_4MXM5LfwTdUno1sMIBvGQ8M_PcYXt5DGGy8hJCfhfCvn8Pt95yc236G8GQsYo3mNLthAExdvC7gx4CyKeUqQB2QVVMTIdXFgq16yDFaOVMpxj0OnqgAZgwKp_TrzN0DgzZChb6UJyauu9Gl7xZFv5tsUU-Wpq5zTiY5DPLQcxTNLZZ4UXNvgS7DeWRdPJ_yg1bFroV-4%253D&amp;source=gmail&amp;ust=1655616138544000&amp;usg=AOvVaw3R0sNrjI68wzw-61Yi5Ekp\"><img height=\"102\" width=\"200\" alt=\"\" style=\"display:block;margin:20px auto 0\" src=\"https://thietkelogo.vn/wp-content/uploads/2016/02/logo-dep.png\" class=\"CToWUd\"></a>\r\n"
				+ "                                        </center>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </tbody>\r\n"
				+ "                        </table>\r\n"
				+ "                        <table style=\"margin:0 auto;max-width:100%;width:600px\">\r\n"
				+ "                            <tbody>\r\n"
				+ "                                <tr>\r\n"
				+ "                                    <td>\r\n"
				+ "                                        <table bgcolor=\"white\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"background:white;border-radius:8px;border:0;margin:0px auto;width:100%\">\r\n"
				+ "                                            <tbody>\r\n"
				+ "                                                <tr>\r\n"
				+ "                                                    <td>\r\n"
				+ "                                                        <table border=\"0\" cellpadding=\"30\" cellspacing=\"0\">\r\n"
				+ "                                                            <tbody>\r\n"
				+ "                                                                <tr>\r\n"
				+ "                                                                    <td style=\"color:#444444;font-size:18px;line-height:24px\">\r\n"
				+ "                                                                        <center>\r\n"
				+ "                                                                            <p style=\"color:#444444;text-align:left;line-height:22px;font-size:16px\">\r\n"
				+ "                                                                                <font color=\"#444444\">Chào "+name+",</font>\r\n"
				+ "                                                                            </p>\r\n"
				+ "                                                                            <p style=\"color:#444444;text-align:left;line-height:22px;font-size:16px\">\r\n"
				+ "                                                                                <font color=\"#444444\">Hệ thống SkyPhone xin thông báo đến bạn. Vấn đề truy cập vào tài khoản của bạn từ webside SkyPhone.</font>\r\n"
				+ "                                                                            </p>\r\n"
				+ "                                                                            <center>\r\n"
				+ "                                                                                <table border=\"0\" cellpadding=\"14\" cellspacing=\"0\" style=\"background:#0079bf;border-radius:6px;color:#ffffff;display:inline-block;font-size:20px;font-weight:bold;line-height:24px;margin:5px auto;text-align:center\">\r\n"
				+ "                                                                                    <tbody>\r\n"
				+ "                                                                                        <tr>\r\n"
				+ "                                                                                            <td align=\"center\" style=\"vertical-align:middle\">\r\n"
				+ "                                                                                                <font color=\"#444444\">\r\n"
				+ "                                                                                                    <a style=\"text-decoration:none;padding:0 8px\" href=\"http://localhost:8080/SkyPhone/index\" target=\"_blank\" data-saferedirecturl=\"http://localhost:8080/SkyPhone/index\">\r\n"
				+ "                                                                                                        <font color=\"white\">Đăng nhập vào SkyPhone →</font>\r\n"
				+ "                                                                                                    </a>\r\n"
				+ "                                                                                                </font>\r\n"
				+ "                                                                                            </td>\r\n"
				+ "                                                                                        </tr>\r\n"
				+ "                                                                                    </tbody>\r\n"
				+ "                                                                                </table>\r\n"
				+ "                                                                            </center>\r\n"
				+ "                                                                            <p style=\"color:#444444;text-align:left;line-height:22px;font-size:16px\">\r\n"
				+ "                                                                                <font color=\"#444444\">SkyPhone đã nhận được yêu cầu thay đổi mật khẩu của quý khách:</font>\r\n"
				+ "                                                                            </p>\r\n"
				+ "                                                                            <p style=\"color:#444444;text-align:left;line-height:22px;font-size:16px\"></p>\r\n"
				+ "\r\n"
				+ "                                                                            <p style=\"color:#444444;font-size:18px;line-height:24px\"></p>\r\n"
				+ "                                                                            <p style=\"color:#444444;text-align:left;line-height:22px;font-size:16px\">\r\n"
				+ "                                                                                <font color=\"#444444\">Lưu ý không cung mật khẩu mặc định của hệ thống SkyPhone gửi đến bạn! <a href=\"\" target=\"_blank\" data-saferedirecturl=\"\">Hệ thống SkyPhone sẽ hỗ trợ bạn</a>\r\n"
				+ "                                                                                    <img data-emoji=\"☺\" class=\"an1\" alt=\"☺\" aria-label=\"☺\" src=\"https://fonts.gstatic.com/s/e/notoemoji/14.0/263a/72.png\" loading=\"lazy\">.</font>\r\n"
				+ "                                                                            </p>\r\n"
				+ "                                                                            <p style=\"color:#444444;text-align:left;line-height:22px;font-size:16px\">\r\n"
				+ "                                                                                <font color=\"#444444\">Lưu ý: mật khẩu chỉ có hiệu lực trong vòng 30 phút SkyPhone.</font>\r\n"
				+ "                                                                            </p>\r\n"
				+ "                                                                            <h3>Mật khẩu đặt lại: </h3>\r\n"
				+ "                                                                            <h2>123@123</h2>\r\n"
				+ "                                                                        </center>\r\n"
				+ "                                                                    </td>\r\n"
				+ "                                                                </tr>\r\n"
				+ "                                                            </tbody>\r\n"
				+ "                                                        </table>\r\n"
				+ "                                                    </td>\r\n"
				+ "                                                </tr>\r\n"
				+ "                                            </tbody>\r\n"
				+ "                                        </table>\r\n"
				+ "                                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"12\" style=\"border:0;font-size:14px;line-height:20px;max-width:100%;width:100%\">\r\n"
				+ "                                            <tbody>\r\n"
				+ "                                                <tr>\r\n"
				+ "                                                    <td>\r\n"
				+ "                                                        <center>\r\n"
				+ "                                                            <font color=\"#777777\"><em>Muốn thêm?<br> Xem các sản phẩm của SkyPhone </em></font>\r\n"
				+ "                                                            <a style=\"color:#777777\" href=\"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://i.trellomail.com/u/click?_t%3Dcce9a87967214196ae72b08c7283fda9%26_m%3Dd82e0b9ebebb45b7ac464b16b74c73eb%26_e%3DnKt1uyMrDz_riD8Kd73sWaxD7L90pL2AlFkYqLCTkVplhZ3pODHtQvPLv03WkHMTmn8YChUeWu5i90OgoX_MxM6R9817RkvKF_VNYHsN2Ie733dw3KL9sJVsxj0x9fKgx_VZHpUTilZv9sF1zRtRD3jylPO6cnlA78hkqBVY-eNvJV36i2v-tzwI6wvfERoSqXJmw5byt0JlS90qv_5Vw0hjXLVfhQG217PicFwYzOYWdfnb5C_D71gDMdiQ1aIZWxZPUPfZUrthSF_J5cHHswAUBPyu3UAGVulXhEVP2r14VcCHARzXwsU2TkyPCi78gzQeI6HC2hd2b1OmVtysDUOjBrHSP6BlGMjn-D_yDgci7tJKAi4nRjCw6Ufa026Q&amp;source=gmail&amp;ust=1655616138544000&amp;usg=AOvVaw39XQotlgkkr2nRWlsQE5_H\">\r\n"
				+ "                                                                <font color=\"#777777\"><em>SkyPhone product →</em></font>\r\n"
				+ "                                                            </a>\r\n"
				+ "                                                        </center>\r\n"
				+ "                                                    </td>\r\n"
				+ "                                                </tr>\r\n"
				+ "                                                <tr>\r\n"
				+ "                                                    <td>\r\n"
				+ "                                                        <center>\r\n"
				+ "                                                            <a style=\"text-decoration:none;padding:8px 0\" href=\"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://i.trellomail.com/u/click?_t%3Dcce9a87967214196ae72b08c7283fda9%26_m%3Dd82e0b9ebebb45b7ac464b16b74c73eb%26_e%3DnKt1uyMrDz_riD8Kd73sWdfh-xyf92hvtUSrZbksS7r-xrjpTdRJXnQImN8ci6w30dJO6A0FicluRhhYVr9MVKk7GxQShSa_m_XV33k8oYDqfGg69XoQ7jejqf7oEsKYlnAl3p4ZYerTaKHQvPWiPCUgU_qr9FLihgQuK38vBtX2B5_ATQOihoq9kkY3AKYQ8SXPmWLFRcNla7WKWbIbl184YRbO6Tgdwvmw3dyRgZmocZ7FzDDcMTrjg7lYP8uByBseyy4y4v7fDlnTrohD8g%253D%253D&amp;source=gmail&amp;ust=1655616138544000&amp;usg=AOvVaw1SqqZh5mkxAEPhHY3m9oTR\"><img width=\"32\" height=\"32\" alt=\"\" style=\"display:inline\" src=\"https://ci3.googleusercontent.com/proxy/gaTzIwXYgS-BZSovoWDEMJAgklnvHRuCpCbeWBmhi4gUCI6J8uZPlj64CgSKo6UEhIN3S8XN3mG7Lj2n5pmWX5PWG3FPxXI4QKKJDacsfaFL_4jGy7so=s0-d-e1-ft#https://dm9d104elaxz5.cloudfront.net/Plaintext_blue%20buttons/fb.png\"\r\n"
				+ "                                                                    class=\"CToWUd\"></a>&nbsp;&nbsp;\r\n"
				+ "                                                            <a style=\"text-decoration:none;padding:8px 0\" href=\"\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://i.trellomail.com/u/click?_t%3Dcce9a87967214196ae72b08c7283fda9%26_m%3Dd82e0b9ebebb45b7ac464b16b74c73eb%26_e%3DnKt1uyMrDz_riD8Kd73sWW_wfLiiZ5rTqoag9uXbtzR5YaASDnGK4zeg3QqRVsOnFUJd0Jof5UO-Dt_FMCo7lcBvUzxaZdouIte2wLt-P6Vv5O6D_s6FSXSIzwsBQgLavHvTzXB_pGXWurKPIngaVaBo58SVEDNRqP_Xy8XZxZe7vknE5fFQBI0lKP4BzlpvHQ8VtK3VFmQ_5nnG4iSObB5QwqIj_wDxVlh46A55mZPw5z_TwDEJETIn6_oep3zUe3c1k6mIv_IS6sF2LVjohA%253D%253D&amp;source=gmail&amp;ust=1655616138545000&amp;usg=AOvVaw0PH76FaL8qTFOdOMarhVo5\">\r\n"
				+ "                                                                <img width=\"32\" height=\"32\" alt=\"\" style=\"display:inline\" src=\"https://ci5.googleusercontent.com/proxy/fE060TO2gyz1QTuf2ltNw4Y50ee6mcXWxsBWLnF9JUwOcP4rWS6BKrpDfr9FEyqYzjRNkHh9J7Z-l3ce8pBCGm-8Id8zPNZq6nYi2gasYv4ZLchLxonGfYobjN4=s0-d-e1-ft#https://dm9d104elaxz5.cloudfront.net/Plaintext_blue%20buttons/twitter.png\"\r\n"
				+ "                                                                    class=\"CToWUd\"></a>&nbsp;&nbsp;\r\n"
				+ "                                                            <a style=\"text-decoration:none;padding:8px 0\" href=\"\r\n"
				+ "                                                                target=\" _blank \" data-saferedirecturl=\"https://www.google.com/url?q=https://i.trellomail.com/u/click?_t%3Dcce9a87967214196ae72b08c7283fda9%26_m%3Dd82e0b9ebebb45b7ac464b16b74c73eb%26_e%3DnKt1uyMrDz_riD8Kd73sWRt5Yrcy13K8GNak9VdTJTJIzvmiRi2A3Kj-XP6ECQzBT28n_TBGLyhf9gqVMCWJuODwblgRqc2QPr4FMpnU-8_fRKQSgMI9UZoI002G7f6q6hZ8XVtc4q5vRkIjSyj0LOV3xhbxMayXuoLZBq_z4SdNmQCvUri-DXgxVBqYRK89FeyXNFAKBLWJqhlr85zrHB2gzGK8doFhetfJH8fCD0-5kYx1tTOPkoK_SpSNHQABpuDWvxDqMSXbextYtFoltxcJJjYVuTxNQJ1vLxDJMkw%253D&amp;source=gmail&amp;ust=1655616138545000&amp;usg=AOvVaw34rwVqdCh9zULDVNpAOkrf \">\r\n"
				+ "                                                                <img width=\"32 \" height=\"32 \" alt=\" \" style=\"display:inline \" src=\"https://ci6.googleusercontent.com/proxy/oeEuH6RkSL3DnUUmgDQIKe_w7A8WrAAh38HDjGJXoPa5WnnjDEE8iInGhKeNcDvl2-p58p1KYxIKYjC7YrsH0sZ4VrHRNEoQnPKqgqFGQw3-_TjlanrXuDSMpGPJ=s0-d-e1-ft#https://dm9d104elaxz5.cloudfront.net/Plaintext_blue%20buttons/linkedin.png \"\r\n"
				+ "                                                                    class=\"CToWUd \"></a>&nbsp;&nbsp;\r\n"
				+ "                                                            <a style=\"text-decoration:none;padding:8px 0 \" href=\" \"\r\n"
				+ "                                                                target=\"_blank \" data-saferedirecturl=\"https://www.google.com/url?q=https://i.trellomail.com/u/click?_t%3Dcce9a87967214196ae72b08c7283fda9%26_m%3Dd82e0b9ebebb45b7ac464b16b74c73eb%26_e%3DnKt1uyMrDz_riD8Kd73sWTispRWWwMEtCkHGgKF_Oiofp818rJ4yjk8fHjMfQZyfsPwFcPJ9D0L7gHqRh2UBu1F0MqMaw9icBNRQsmIDn-hKCwvSfkzq38c8wLbny1DiNI6iVCTMVypw-WRaMRjqVtEoxAeIJVXwTk-_aVH_DaktcLQpqw90CAxKb3TO8RrJXp_EzCFleffht290Xi-KkwKW_VhjUgYWc-dg_nXtisPXVJRu2CszXlVD-n-WZwUHia3urG9RgMvFRzgCAwGOUA%253D%253D&amp;source=gmail&amp;ust=1655616138545000&amp;usg=AOvVaw3gUs-Ik9BMtD3dY-f_rH1N \">\r\n"
				+ "                                                                <img width=\"32 \" height=\"32 \" alt=\" \" style=\"display:inline \" src=\"https://ci3.googleusercontent.com/proxy/-WdOA81mUfH0-oRw0hEQxxEaw3K-xgmXserSktQtQX6VmaIfoh7ZCO-JIGxf6qbV3SBK1dE65trXa0H7eAqgQe4r1DrVyBZiWESN9eeNhOXhtCUSYIku8m90=s0-d-e1-ft#https://dm9d104elaxz5.cloudfront.net/Plaintext_blue%20buttons/insta.png \"\r\n"
				+ "                                                                    class=\"CToWUd \"></a>\r\n"
				+ "                                                        </center>\r\n"
				+ "                                                    </td>\r\n"
				+ "                                                </tr>\r\n"
				+ "                                                <tr>\r\n"
				+ "                                                    <td>\r\n"
				+ "                                                        <center>\r\n"
				+ "                                                            <font color=\"#777777 \"><em>Bản quyền © 2021 SkyPhone Inc., Bảo lưu toàn quyền.<br> 888 Broadway Floor 4 New York, NY 10003</em></font>\r\n"
				+ "                                                        </center>\r\n"
				+ "                                                    </td>\r\n"
				+ "                                                </tr>\r\n"
				+ "                                                <tr>\r\n"
				+ "                                                    <td>\r\n"
				+ "                                                        <center>\r\n"
				+ "                                                            <a href=\" \" target=\"_blank \" data-saferedirecturl=\"https://www.google.com/url?q=https://i.trellomail.com/e/evib?_t%3Dcce9a87967214196ae72b08c7283fda9%26_m%3Dd82e0b9ebebb45b7ac464b16b74c73eb%26_e%3DT0IlUq_i4WSUGuIVhcSn_1w7F95dplBXLs69q1h0-7Hks-M_f9gRah0DXhDHUusm3oAda1PnS53jy6cBgBub_8uRLSrKnVE_igsX5Sqg-ouUWVE0pyy-GPLt6vn29tVY&amp;source=gmail&amp;ust=1655616138545000&amp;usg=AOvVaw2_pjQRruU-YasUHLqIUEzb \">\r\n"
				+ "                                                                <font color=\"#aaaaaa \"><em>Xem bằng trình duyệt</em></font>\r\n"
				+ "                                                            </a>\r\n"
				+ "                                                            <font color=\"#aaaaaa \">|</font>\r\n"
				+ "                                                            <a href=\" \" target=\"_blank \" data-saferedirecturl=\"https://www.google.com/url?q=https://i.trellomail.com/a/click?_t%3Dcce9a87967214196ae72b08c7283fda9%26_m%3Dd82e0b9ebebb45b7ac464b16b74c73eb%26_e%3DnKt1uyMrDz_riD8Kd73sWTWEhTbX9UGdNiGgGb93jLvCnrqOvAjYLyLQ6HP4j01jckoqZpGZC_v6j8hQ77VJqfcWYTCGYlzJILtZ6Nx4DeIt2Qix-RPxD2XbORgVaO3hgh2wFA7UgQugu-jIRtW7xuGq_pD8Pxk48zNPkMZsB-Klre0F476H8MsDElBrv9ThsIC0h_oT-MeRi7n-H1XshhxW5vzHl81D2djwTkckjnfzgb_s0iJQ67wfKmSrDicxCL2IPKgV_p_HUGZxWI1iVLumEniZjyWzw7X2udGZnbjShu7P6CDMbbzUs3gQRlCp9xjPQlo_t5vBYFWGIdigcEjUrEsSXuV7_blUS3O5_m4ZCWdlPUCjc0Ixj3VpH77o&amp;source=gmail&amp;ust=1655616138545000&amp;usg=AOvVaw1M5_URBS1nM5mMe8Pie-EA \">\r\n"
				+ "                                                                <font color=\"#aaaaaa \"><em>Chính sách Bảo mật</em></font>\r\n"
				+ "                                                            </a>\r\n"
				+ "                                                            <font color=\"#aaaaaa \">|</font>\r\n"
				+ "                                                            <a href=\" \" target=\"_blank \" data-saferedirecturl=\"https://www.google.com/url?q=https://i.trellomail.com/a/click?_t%3Dcce9a87967214196ae72b08c7283fda9%26_m%3Dd82e0b9ebebb45b7ac464b16b74c73eb%26_e%3DnKt1uyMrDz_riD8Kd73sWYmvWAGtW3ACPbZ3iMIM7qLalkq_u0w9f-QXVDCnGbGP2POyabVoC8HLrVIuF8uuPnUqNGEUzzkYPjbYylvwKEPQ7mkaTJQfdTsnRy23SfUz0tn5AJIwAtei3B40s63oK9ScBavF0DHryufh3FF6LKVwamPVB9iQ3VhQHDKRnXPO6LObnWwqHcRp8APm1nN76stVNN7OlPHqX93PRfJnwMKAnaxoJR65Hu2_ZopdN4ccGal2Pbb3nL7uQJs8AeT5dIsv0KE7AQ_EcDtT7NA7rlHTl1ATLUlRtZMQMdFrpfwbhj80sJHxZRU8VQjzOwTNh6YJKt62966bnpTgPhmZ9TVbP28vCH_K1jC0Ox5x-0r5&amp;source=gmail&amp;ust=1655616138545000&amp;usg=AOvVaw0TGCYuT1EIa7-3hziRKFcz \">\r\n"
				+ "                                                                <font color=\"#aaaaaa \"><em>Quản lý Tài khoản</em></font>\r\n"
				+ "                                                            </a>\r\n"
				+ "                                                            <font color=\"#aaaaaa \">|</font>\r\n"
				+ "                                                            <a href=\" \">\r\n"
				+ "                                                                <font color=\"#aaaaaa \"><em>Bỏ theo dõi</em></font>\r\n"
				+ "                                                            </a>\r\n"
				+ "                                                        </center>\r\n"
				+ "                                                    </td>\r\n"
				+ "                                                </tr>\r\n"
				+ "                                            </tbody>\r\n"
				+ "                                        </table>\r\n"
				+ "                                    </td>\r\n"
				+ "                                </tr>\r\n"
				+ "                            </tbody>\r\n"
				+ "                        </table>\r\n"
				+ "                    </center>\r\n"
				+ "                </td>\r\n"
				+ "            </tr>\r\n"
				+ "        </tbody>\r\n"
				+ "    </table>";
		return body;
	}
}
