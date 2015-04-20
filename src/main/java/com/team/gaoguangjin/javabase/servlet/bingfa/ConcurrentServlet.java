package com.team.gaoguangjin.javabase.servlet.bingfa;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import com.team.gaoguangjin.jdbc.normal.NormalJDBC;

/**
 * @ClassName:ConcurrentServlet.java
 * @Description: 学习并发
 * @author gaoguangjin
 * @Date 2015-4-16 下午2:47:32
 */
@Slf4j
public class ConcurrentServlet extends HttpServlet {
	static CyclicBarrier sb = new CyclicBarrier(20);
	static CyclicBarrier sb2 = new CyclicBarrier(5);
	// 使用fifo先进先出的队列
	Queue<HttpServletRequest> queue = new LinkedList<HttpServletRequest>();
	// 五个阻塞队列
	BlockingQueue<HttpServletRequest> block = new ArrayBlockingQueue<HttpServletRequest>(2);
	
	Map<HttpServletRequest, Boolean> map = new HashMap<HttpServletRequest, Boolean>();
	
	// 参数有 isConCurrent=y&id=?&method=buy
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");
		if ("buy".equals(method)) {// 购买
			buy(req, resp);
		} else if ("queue".equals(method)) {// 将并发请求放到队列里面，取到一个返回一个
			// queue.add(req);
			try {
				block.put(req);
			} catch (InterruptedException e1) {
			}
			map.put(req, false);
			// 先打开浏览器输入a,然后输入b，然后再输入c测试队列用的
			if ("a".equals(req.getParameter("message"))) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if ("b".equals(req.getParameter("message"))) {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			fifo(req, resp);// 先进先出处理
		} else {
			pessimistic(req, resp);// 悲观锁的方式
		}
	}
	
	// b进入fifo方法
	// 开始处理队列：a的请求！
	// c进入fifo方法
	// 开始处理队列：b的请求！
	// a进入fifo方法
	// 开始处理队列：c的请求！
	
	private void fifo(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		try {
			System.out.println(req.getParameter("message") + "进入fifo方法");
			String message = "";
			HttpServletRequest request = null;
			while (!block.isEmpty()) {
				request = block.take();
				dealQueue(request);
			}
			if (map.get(req)) {
				message = "处理成功";
			} else {
				message = "等待！";
			}
			req.setAttribute("message", message);
			pessimistic(req, resp);
		} catch (Exception e) {
			log.error("fifo队列访问失败：" + e.getMessage());
		}
	}
	
	private void dealQueue(HttpServletRequest request) throws InterruptedException {
		String message = request.getParameter("message");
		System.out.println("开始处理队列：" + message + "的请求！查询到剩余1本书");
		map.put(request, true);
		Thread.sleep(1000);// 休眠
		System.out.println("处理购买【" + message + "】书");
	}
	
	/**
	 * @Description: 购买
	 * @param req
	 * @param resp
	 * @return:void
	 */
	private void buy(HttpServletRequest request, HttpServletResponse response) {
		Connection conn = null;
		String message = "";
		try {
			conn = NormalJDBC.getConnection();
			String id = request.getParameter("id");
			String isConCurrent = request.getParameter("isConCurrent");
			if ("y".equals(isConCurrent)) {
				message = "测试并发";
				// 开启线程模拟并发,如果使用线程的话，前台会先返回结果，后台会继续再处理,使用这个方法的话，就不用前台同时开启两个浏览器了
				for (int i = 0; i < 5; i++)
					useConcurrentTestUpdate(i, id, NormalJDBC.getConnection());// 每个线程都会关闭数据库连接，这样让数据库锁起作用，不共享一个session
			} else {
				message = upadteRemainBoook(id, conn);
			}
			request.setAttribute("message", message);
			pessimistic(request, response);
			System.out.println("返回前台结果！");
		} catch (Exception e) {
			log.error("悲观锁查询失败：" + e.getLocalizedMessage());
		}
		
	}
	
	// 模拟并发，不用手动同时开启两个浏览器
	private void useConcurrentTestUpdate(int i, final String id, final Connection conn) {
		new Thread(i + "线程") {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + "线程睡眠");
					Thread.sleep(10000);
					sb2.await();
					upadteRemainBoook(id, conn);// 达到零界点时候同时执行这个方法
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	
	/**
	 * @Description:
	 * @param request
	 * @param conn
	 * @throws SQLException
	 * @return:String
	 * @see:开启两个浏览器，同时访问页面，看到某本书剩余一本，然后同时点击后面，如果没有加数据库锁，会导致两个人都购买成功。
	 */
	private String upadteRemainBoook(String id, Connection conn) throws SQLException {
		String message = "";
		// 使用悲观锁,如果一个用户点击购买的时候，同时另外一个用户也点击购买，但是先点击购买的，会在查询的时候获到锁，第二个就会等待
		PreparedStatement ps = conn.prepareStatement("select bookRemainNumber from gao.tcbooklock  where id=? for update");
		// 没使用锁，同时查询到剩余一本书，同时去更新,然后前台同时;
		// PreparedStatement ps = conn.prepareStatement("select bookRemainNumber from gao.tcbooklock  where id=? ");
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		int sum = -1;
		while (rs.next()) {
			sum = Integer.parseInt(rs.getString("bookRemainNumber"));
		}
		System.out.println(Thread.currentThread().getName() + "==sum=" + sum + new Date() + "开始查询sum的值，对表数据加锁");
		try {
			Thread.sleep(3000);// 睡眠2miao，手动开启两个浏览器的时候，将这个注释打开，如果用线程模拟并发就注释掉
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (sum == 0) {
			message = "剩余数量为0，无法购买！";
			log.info(Thread.currentThread().getName() + ":" + message);
		} else if (sum == -1) {
			message = "改id书籍已经下架了";
			log.info("message");
		} else {
			message = "购买成功！";
			ps = conn.prepareStatement("update gao.tcbooklock set bookremainnumber=? where id =? ");
			ps.setString(1, (sum - 1) + "");
			ps.setString(2, id);
			ps.executeUpdate();
			log.info(Thread.currentThread().getName() + " :购买成功！");
		}
		
		// conn.commit();默认自动提交的
		conn.close();
		return message;
	}
	
	/**
	 * @Description:
	 * @param req
	 * @param resp
	 * @return:void
	 * @see：forward方式：request.getRequestDispatcher("/somePage.jsp").forwardrequest, response);，可以带参数跳转
	 * @see:redirect方式：response.sendRedirect("/somePage.jsp");直接跳转
	 */
	private void pessimistic(HttpServletRequest request, HttpServletResponse response) {
		Connection conn = null;
		try {
			conn = NormalJDBC.getConnection();
			List<Book> bookList = getList(conn);
			request.setAttribute("bookList", bookList);
			request.getRequestDispatcher("/gaoguangjin/bingfa/book.jsp").forward(request, response);
		} catch (Exception e) {
			log.error("悲观锁查询失败：" + e.getLocalizedMessage());
		}
		finally {
			try {
				conn.close();// 如果conn关闭的话，就等于oracle的session关闭了，就看不到锁的效果了
			} catch (SQLException e) {
				log.error("悲观锁数据库连接关闭失败：" + e.getLocalizedMessage());
			}
		}
	}
	
	/**
	 * @Description: 查询bookList
	 * @param conn
	 * @return:List<Book>
	 * @throws SQLException
	 */
	private List<Book> getList(Connection conn) throws Exception {
		List<Book> bookList = new ArrayList<Book>();
		PreparedStatement ps = conn.prepareStatement("select * from gao.tcbooklock");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			bookList.add(getBookBean(rs));
		}
		return bookList;
	}
	
	/**
	 * @Description: 反射获取bean
	 * @param rs
	 * @return:Book
	 * @throws SQLException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private Book getBookBean(ResultSet rs) throws SQLException, IllegalArgumentException, IllegalAccessException {
		try {
			Book book = new Book();
			Class<Book> bookStaticClasss = Book.class;// 静态加载
			Book bookReflect = bookStaticClasss.newInstance();
			// Class<? extends Book> classss = book.getClass();// 和下面的类似，不过这个是静态加载
			// 动态的加载类名，比如我们在不知道类名的情况下，可以传过来一个类名的字符串。
			Class<? extends Class> classForNameObject = Class.forName("com.team.gaoguangjin.javabase.servlet.bingfa.Book").getClass();
			
			// 赋值
			Field[] fieldList = bookStaticClasss.getFields();
			for (Field field : fieldList) {
				field.set(bookReflect, rs.getString(field.getName()));
			}
			return bookReflect;
		} catch (Exception e) {
		}// 动态加载
		return null;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, res);
	}
	
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 20; i++)
			concurrentEnvironment(i);
	}
	
	private static void concurrentEnvironment(int i) {
		new Thread(i + "线程") {
			public void run() {
				try {
					Thread.sleep(1000);
					sb.await();
					domethod();// 达到零界点时候同时执行这个方法
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	
	protected static void domethod() {
		System.out.println(Thread.currentThread().getName() + ":" + new Date());
		// 打印的结果
		// 8线程:Thu Apr 16 15:14:22 CST 2015
		// 4线程:Thu Apr 16 15:14:22 CST 2015
		// 14线程:Thu Apr 16 15:14:22 CST 2015
		// 1线程:Thu Apr 16 15:14:22 CST 2015
		// 18线程:Thu Apr 16 15:14:22 CST 2015
		// 7线程:Thu Apr 16 15:14:22 CST 2015
		// 6线程:Thu Apr 16 15:14:22 CST 2015
		// 0线程:Thu Apr 16 15:14:22 CST 2015
		// 16线程:Thu Apr 16 15:14:22 CST 2015
		// 11线程:Thu Apr 16 15:14:22 CST 2015
		// 12线程:Thu Apr 16 15:14:22 CST 2015
		// 3线程:Thu Apr 16 15:14:22 CST 2015
		// 17线程:Thu Apr 16 15:14:22 CST 2015
		// 5线程:Thu Apr 16 15:14:22 CST 2015
		// 2线程:Thu Apr 16 15:14:22 CST 2015
		// 10线程:Thu Apr 16 15:14:22 CST 2015
		// 19线程:Thu Apr 16 15:14:22 CST 2015
		// 13线程:Thu Apr 16 15:14:22 CST 2015
		// 9线程:Thu Apr 16 15:14:22 CST 2015
		// 15线程:Thu Apr 16 15:14:22 CST 2015
	}
	
}
