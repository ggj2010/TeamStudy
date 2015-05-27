//package com.team.gaoguangjin.soa.qbit;
//
//import io.advantageous.qbit.annotation.RequestMapping;
//import io.advantageous.qbit.annotation.RequestMethod;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RequestMapping("/todo-service")
//public class TodoService {
//	
//	private List<TodoItem> todoItemList = new ArrayList<>();
//	
//	@RequestMapping("/todo/count")
//	public int size() {
//		
//		return todoItemList.size();
//	}
//	
//	@RequestMapping("/todo/")
//	public List<TodoItem> list() {
//		
//		return todoItemList;
//	}
//	
//	@RequestMapping(value = "/todo", method = RequestMethod.POST)
//	public void add(TodoItem item) {
//		
//		todoItemList.add(item);
//	}
// }
