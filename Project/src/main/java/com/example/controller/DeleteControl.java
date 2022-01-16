package com.example.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.FileData;
import com.example.service.FileService;
@WebServlet("/delete")
public class DeleteControl extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println(name);
		FileService fs = new FileService();
		try {
			fs.deleteFile(name);
			ArrayList<FileData> fArr = fs.getFiles();
			ArrayList<FileData> fixedArr = new ArrayList<FileData>();
			ArrayList<FileData> unfixedArr = new ArrayList<FileData>();
			for(FileData fd : fArr) {
				if(fd.getFix()==1) {
					fixedArr.add(fd);
				} else {
					unfixedArr.add(fd);
				}
			}
			int count = fs.count();
			request.setAttribute("fixedArr", fixedArr);
			request.setAttribute("unfixedArr", unfixedArr);
			request.setAttribute("count", count);
			request.setAttribute("state", true);	
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
