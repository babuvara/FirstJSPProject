package com.chainsys.jspproject;

import java.io.IOException;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.jspproject.common.util.InvalidInputDataException;
import com.chainsys.jspproject.common.util.LogManager;
import com.chainsys.jspproject.common.util.ExceptionManager;
import com.chainsys.jspproject.common.util.HTMLHelper;
import com.chainsys.jspproject.common.util.Validator;
import com.chainsys.jspproject.dao.EmployeeDao;
import com.chainsys.jspproject.pojo.Employee;

@WebServlet("/Employees")
public class Employees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Employees() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		List<Employee> allEmployees = EmployeeDao.getAllEmployee();
		Iterator<Employee> empIterator = allEmployees.iterator();
		while (empIterator.hasNext()) {
			Employee result = empIterator.next();
//			out.println("Employee id: " + "\t" + "Employee first name: " + "\t" + "Employee last name: " + "\t"
//					+ "Employee email: " + "\t" + "Employee hiredate: " + "\t" + "Employee job id: " + "\t"
//					+ "Employee salary: " + "\t");
			out.println("<hr/>");

			out.println(result.getEmp_ID() + "," + result.getFirst_name() + "," + result.getLast_name() + ","
					+ result.getEmail() + "," + result.getHire_date() + "," + result.getJob_id() + ","
					+ result.getSalary() + ",");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		String submitValue = request.getParameter("submit");
		System.out.println(submitValue);
		if (submitValue.equals("UPDATE")) {
			doPut(request, response);
		} else if (submitValue.equals("DELETE")) {
			doDelete(request, response);
		} else if (submitValue.equals("ADD")) {
			String source = "AddNewEmployee";
			String message = "<h1>Error while" + source + "</h1>";
			Employee newemp = null;
			int result = 0;
			int empId = 0;
//			String testname = null;
			try {
				newemp = new Employee();
				String id = request.getParameter("id");
				try {
					Validator.checkStringForParse(id);
				} catch (InvalidInputDataException err) {
					message += "Error in Employee id input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					// err.printStackTrace();
					return;
				}
				empId = Integer.parseInt(id);
				try {
					Validator.CheckNumberForGreaterThanZero(empId);
				} catch (InvalidInputDataException err) {
					message += "Error in Employee id input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					// err.printStackTrace();
					return;
				}
				newemp.setEmp_ID(empId);
//------------------------------------------------------------------------------
				String fname = request.getParameter("fname");
//				testname = fname;
				try {
					Validator.checkStringOnly(fname);
				} catch (InvalidInputDataException e) {
					message += "Error in Employee firstname input </p>";
					String errorPage = ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					// err.printStackTrace();
					return;
				}
				try {
					Validator.checklengthOfString(fname);
				} catch (InvalidInputDataException err) {
					message += "Error in Employee firstname input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					// err.printStackTrace();
					return;
				}
				newemp.setFirst_name(fname);
//-----------------------------------
				String lname = request.getParameter("lname");
				// testname = fname;
				try {
					Validator.checkStringOnly(lname);
				} catch (InvalidInputDataException err) {
					message += "Error in Employee lastname input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					// err.printStackTrace();
					return;
				}
				try {
					Validator.checklengthOfString(lname);
				} catch (InvalidInputDataException err) {
					message += "Error in Employee lastname input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					// err.printStackTrace();
					return;

				}
				newemp.setLast_name(lname);
//----------------------------------------------------------			
				String email = request.getParameter("email");
				try {
					Validator.checkMail(email);
				} catch (InvalidInputDataException e) {
					message += "Error in Employee email input </p>";
					String errorPage = ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					// err.printStackTrace();
					return;
				}
				newemp.setEmail(email);
//------------------------------------------------------------			
				SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
				String hd = request.getParameter("hdate");
				// Date hire_date=hire_dateFormate.parse(emp_HireDate);

				try {
					Validator.checkDateFormat(hd);
				} catch (InvalidInputDataException e) {
					message += "Error in Employee hirdate input </p>";
					String errorPage = ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					// err.printStackTrace();
					return;
				}
				Date newDate = null;
				newDate = hire_dateFormate.parse(hd);

				newemp.setHire_date(newDate);
//--------------------------------------------------------------
				String jobId = request.getParameter("jobid");
				try {
					Validator.checkjob(jobId);
				} catch (InvalidInputDataException err) {
					message += "Error in Employee jobid input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					// err.printStackTrace();
					return;
				}
				newemp.setJob_id(jobId);
//--------------------------------------------------------------			
				String sal = request.getParameter("salary");
				float salParse = Float.parseFloat(sal);
				try {
					Validator.checkfee(salParse);
				} catch (InvalidInputDataException err) {
					message += "Error in Employee salary input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					// err.printStackTrace();
					return;
				}
				try {
					Validator.checkSalLimit(salParse);
				} catch (InvalidInputDataException err) {
					message += "Error in Employee salary input </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);
					out.print(errorPage);
					// err.printStackTrace();
					return;
				}
				newemp.setSalary(salParse);
//----------------------------------------------			
				result = EmployeeDao.insertEmployee(newemp);
			} catch (Exception e) {
				message += "Error in Employee id input </p>";
				String errorPage = ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			out.println("<div> Add New Employee: " + result + "</div>");
			// + new Employee()); -> from the servlet send only
			// object are illegal.
		} else {
			out.print("<h1> SELECT VALID CHOICE </h1>");
		}

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source = "UpdateEmployee";
		String message = "<h1>Error while" + source + "</h1>";
		Employee newemp = new Employee();
		int result = 0;
		try {

			String id = request.getParameter("id");
			try {
				Validator.checkStringForParse(id);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee id input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			int empId = Integer.parseInt(id);
			try {
				Validator.CheckNumberForGreaterThanZero(empId);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee id input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			newemp.setEmp_ID(empId);
//--------------------------------
			String fname = request.getParameter("fname");
			try {
				Validator.checkStringOnly(fname);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee fname input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			try {
				Validator.checklengthOfString(fname);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee fname input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			newemp.setFirst_name(fname);
//-----------------------------------
			String lname = request.getParameter("lname");
			try {
				Validator.checkStringOnly(lname);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee lname input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			try {
				Validator.checklengthOfString(lname);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee lname input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			newemp.setLast_name(lname);
//----------------------------------			
			String email = request.getParameter("email");
			try {
				Validator.checkMail(email);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee email input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			newemp.setEmail(email);
//--------------------------------------			
			SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
			String emp_HireDate = request.getParameter("hdate");
			// Date hire_date=hire_dateFormate.parse(emp_HireDate);

			try {
				Validator.checkDateFormat(emp_HireDate);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee hdate input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			Date newDate = null;
			newDate = hire_dateFormate.parse(emp_HireDate);
			newemp.setHire_date(newDate);
//----------------------------------------
			String jobId = request.getParameter("jobid");
			try {
				Validator.checkjob(jobId);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee jobid input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			newemp.setJob_id(jobId);
//---------------------------------------			
			String sal = request.getParameter("salary");
			try {
				Validator.checkStringForParse(sal);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee salary input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			float salParse = Float.parseFloat(sal);
			try {
				Validator.checkSalLimit(salParse);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee salary input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			newemp.setSalary(salParse);
//----------------------------------------------	
			result = EmployeeDao.updateEmployee(newemp);
			System.out.println(result + " Updated Successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("<div> Update Employee: " + result + "</div>");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String source = "DeleteEmployee";
		String message = "<h1>Error while" + source + "</h1>";
		int result = 0;
		String id = null;
		try {
			id = request.getParameter("id");
			try {
				Validator.checkStringForParse(id);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee id input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			int empId = Integer.parseInt(id);
			try {
				Validator.CheckNumberForGreaterThanZero(empId);
			} catch (InvalidInputDataException err) {
				message += "Error in Employee id input </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				// err.printStackTrace();
				return;
			}
			result = EmployeeDao.deleteEmployee(empId);
			System.out.println(result + " Deleted Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print("<div> Deleted Employee: " + result + "</div>");
	}

}