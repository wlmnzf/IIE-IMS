package cn.iie.icm.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.iie.icm.action.api.comm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.net.www.protocol.http.HttpURLConnection;

import java.io.*;
import java.net.URL;
import java.util.Map;

@Controller
public class IndexController {
	/**
	 * 跳转到主页
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping("/index")
		private ModelAndView index(Map<String, Object> map,HttpServletRequest request, HttpServletResponse response,
									   String targetUrl,Model model) throws IOException {
		String info = (String)request.getAttribute("info");
		if(info==null||info.isEmpty())
		      map.put("info"," 学生端： stu    123456      管理员端：  admin   123456");

		return new ModelAndView("index");
		}

//	@RequestMapping("/exit")
//	private String exit(Map<String, Object> map,HttpServletRequest request,RedirectAttributes attr, HttpServletResponse response) throws IOException {
//
//		Cookie exit= new Cookie("login",null);
//		exit.setMaxAge(0);
//		exit.setHttpOnly(true);
//		exit.setPath("/");
//		response.addCookie(exit);
//
//		return comm.Login.errRedirect(attr,3);
//	}


	@RequestMapping("/transfer")
	@ResponseBody
	private String transfer(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response
						 ) throws IOException {

		File newfile=null;
//		String targetUrl = request.getParameter("targetUrl");
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"
						+ file.getOriginalFilename();
				newfile=new File(filePath);
				// 转存文件
				file.transferTo(newfile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String line = null;
		String res_str="";
		try {
			// 换行符
			final String newLine = "\r\n";
			final String boundaryPrefix = "--";
			// 定义数据分隔线
			String BOUNDARY = "========7d4a6d158c9";
			// 服务器的域名
			URL url = new URL("http://192.168.210.2/api/sync_image_query.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置为POST请求
			conn.setRequestMethod("POST");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求头参数
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());

			// 上传文件
			StringBuilder sb = new StringBuilder();
			sb.append(boundaryPrefix);
			sb.append(BOUNDARY);
			sb.append(newLine);
			// 文件参数,photo参数名可以随意修改
			sb.append("Content-Disposition: form-data;name=\"file\";filename=\"search.jpg"
					+ "\"" + newLine);
			sb.append("Content-Type:application/octet-stream");
			// 参数头设置完以后需要两个换行，然后才是参数内容
			sb.append(newLine);
			sb.append(newLine);

			// 将参数头的数据写入到输出流中
			out.write(sb.toString().getBytes());

			// 数据输入流,用于读取文件数据
			DataInputStream in = new DataInputStream(new FileInputStream(newfile));
			byte[] bufferOut = new byte[1024];
			int bytes = 0;
			// 每次读1KB数据,并且将文件数据写入到输出流中
			while ((bytes = in.read(bufferOut)) != -1) {
				out.write(bufferOut, 0, bytes);
			}
			// 最后添加换行
			out.write(newLine.getBytes());
			in.close();

			// 定义最后数据分隔线，即--加上BOUNDARY再加上--。
			byte[] end_data = (newLine + boundaryPrefix + BOUNDARY + boundaryPrefix + newLine)
					.getBytes();
			// 写上结尾标识
			out.write(end_data);
			out.flush();
			out.close();

			// 定义BufferedReader输入流来读取URL的响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				res_str=res_str+line;
			}


		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}

		response.setHeader("Access-Control-Allow-Origin", "*");
		System.out.println("..........................res_str:"+res_str);
		return res_str;
	}


}
