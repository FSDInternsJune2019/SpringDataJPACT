package com.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.demo.entities.Batches;
import com.demo.entities.Buyers;
import com.demo.entities.Customers;
import com.demo.entities.Employees;
import com.demo.entities.Items;
import com.demo.entities.Orders;
import com.demo.entities.Products;
import com.demo.entities.Projects;
import com.demo.entities.Students;
import com.demo.enums.TYPE;
import com.demo.repositories.BuyersRepository;
import com.demo.repositories.CustomersRepository;
import com.demo.repositories.EmployeesRepository;
import com.demo.repositories.ProductsRepository;
import com.demo.repositories.StudentsRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication()
@EnableCaching
public class SpringBootDataJpaApplication implements CommandLineRunner{
	/*
	@Autowired
	private EmployeesRepository employeeRepository;
	@Autowired
	private CustomersRepository customersRepository;
	@Autowired
	private StudentsRepository studentsRepository;
	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	private BuyersRepository buyersRepository;*/

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}
    @Transactional
	@Override
	public void run(String... args) throws Exception {
		/*System.out.println("========================findAll===========================");
		employeeRepository.findAll().forEach(System.out::println);
		System.out.println("========================findAll===========================");
		System.out.println("========================findById===========================");
		Optional<Employees> optionalOfEmployees=employeeRepository.findById(1002);
		if(optionalOfEmployees.isPresent())
			System.out.println(optionalOfEmployees.get());
		System.out.println("========================findById===========================");
		
		System.out.println("========================save===========================");
		Employees employees=new Employees();
		employees.setEmpId(1001);
		employees.setEmpName("Rakesh");
		employees.setEmpSalary(32000);
		employees.setEmpDesignation("Tester");
		Employees employeeSaved=employeeRepository.save(employees);
		System.out.println("Employee Saved:"+employeeSaved);
		System.out.println("========================save===========================");

		System.out.println("========================delete===========================");
		employeeRepository.deleteById(1001);
		System.out.println("========================delete===========================");
		
		System.out.println("========================findAll===========================");
		employeeRepository.findAll().forEach(System.out::println);
		System.out.println("========================findAll===========================");
		
		System.out.println("========================update===========================");
		int rows=employeeRepository.update(1002, 95000);
		System.out.println("Rows:"+rows);
		System.out.println("========================update===========================");
		System.out.println("========================findAll===========================");
		employeeRepository.findAll().forEach(System.out::println);
		System.out.println("========================findAll===========================");*/
    	/*
    	Projects projects=new Projects();
    	projects.setProjectCode("P001");
    	projects.setProjectName("BMW");
    	projects.setProjectType(TYPE.BILLABLE);
    	
    	Employees employees=new Employees();
    	employees.setEmpName("Sabbir Poonawala");
    	employees.setEmpSalary(45000);
    	employees.setEmpDesignation("Trainer");
    	employees.setProjects(projects);
    	System.out.println("=======================save======================");
    	Employees employeeSaved=employeeRepository.save(employees);
    	System.out.println("Employee Saved:"+employeeSaved);
    	System.out.println("=======================save======================");

    	System.out.println("==================fetch==========================");
    	
    	Optional<Employees> employeeFound=employeeRepository.findById(employeeSaved.getEmpId());
    	if(employeeFound.isPresent()) {
    		Employees e=employeeFound.get();
    		System.out.println(e);
    		Projects project=e.getProjects();
    		System.out.println(project);
    		
    	}
    	
    	System.out.println("==================fetch==========================");

    	*/
    	/*
    	Items items1=new Items();
    	items1.setItemsCode("Item1-Code");
    	items1.setItemsDescription("Item-1");
    	
    	Items items2=new Items();
    	items2.setItemsCode("Item2-Code");
    	items2.setItemsDescription("Item-2");
    	
    	Items items3=new Items();
    	items3.setItemsCode("Item3-Code");
    	items3.setItemsDescription("Item-3");
    	
    	Items items4=new Items();
    	items4.setItemsCode("items4-Code");
    	items4.setItemsDescription("Item-4");
    	
    	List<Items> itemsList1=Arrays.asList(items1,items2);
    	List<Items> itemsList2=Arrays.asList(items3,items4);
    	
    	Orders orders1=new Orders();
    	orders1.setOrdersId("ODR-1");
    	orders1.setOrdersDescription("Order-1");
    	orders1.setItems(itemsList1);
    	
    	Orders orders2=new Orders();
    	orders2.setOrdersId("ODR-2");
    	orders2.setOrdersDescription("Order-2");
    	orders2.setItems(itemsList2);
    	
    	List<Orders> ordersList=Arrays.asList(orders1,orders2);
    	
    	Customers customer=new Customers();
    	customer.setCustomersId(1001);
    	customer.setCustomersName("Sabbir Poonawala");
    	customer.setOrders(ordersList);
    	
    	System.out.println("==============================Customer Save============================");
    	Customers customerSaved=customersRepository.save(customer);
    	System.out.println("Customer Saved:"+customerSaved);
    	
    	System.out.println("==============================Customer Save============================");
    	
    	System.out.println("==================Customers Fetch=================================");
        Optional<Customers> customersFound=customersRepository.findById(customerSaved.getCustomersId());
        if(customersFound.isPresent()) {
        	
        	Customers c=customersFound.get();
        	System.out.println(c);
        	
        	List<Orders> ordersListFound=c.getOrders();
        	System.out.println(ordersListFound);
        	
        	for(Orders order:ordersListFound) {
        		order.getItems().forEach(System.out::println);
        	}
        	
        }
         
    	System.out.println("==================Customers Fetch=================================");
*/
    	/*
    	
    	Batches batch=new Batches();
    	batch.setBatchCode("INTDE26JFSR001");
    	batch.setBatchTopic("Java Full Stack React");
    	
    	Students student1=new Students();
    	student1.setRollNo(1001);
    	student1.setStudentName("Sabbir");
    	student1.setBatches(batch);
    	
    	Students student2=new Students();
    	student2.setRollNo(1002);
    	student2.setStudentName("Amit");
    	student2.setBatches(batch);
    	System.out.println("=================Students Saved=================================");

    	studentsRepository.save(student1);
    	studentsRepository.save(student2);
    	
    	System.out.println("=================Students Saved=================================");

    	System.out.println("=================Students fetch=================================");

    	Iterable<Students> allStudents=studentsRepository.findAll();
    	for(Students student:allStudents) {
    		System.out.println(student);
    		System.out.println(student.getBatches());
    	}
    	*/
    	/*
    	Buyers buyer1=new Buyers();
    	buyer1.setBuyersId(1);
    	buyer1.setBuyersName("Sabbir");
    	
    	Buyers buyer2=new Buyers();
    	buyer2.setBuyersId(2);
    	buyer2.setBuyersName("Amit");
    	
    	Products product1=new Products();
    	product1.setProductCode("P001");
    	product1.setProductDescription("Product 1");
    	
    	Products product2=new Products();
    	product2.setProductCode("P002");
    	product2.setProductDescription("Product 2");
    	
    	List<Products> productsList1=Arrays.asList(product1,product2);
    	buyer1.setProducts(productsList1);
    	buyer2.setProducts(productsList1);
    	
    	List<Buyers> buyersList1=Arrays.asList(buyer1,buyer2);
    	product1.setBuyers(buyersList1);
    	product2.setBuyers(buyersList1);
    	
    	System.out.println("===============================save==============================");
    	
    	productsRepository.save(product1);
    	productsRepository.save(product2);
    	
    	System.out.println("===============================save==============================");
    	
    	System.out.println("===============================fetch==============================");
    	
    	Iterable<Products> allProducts=productsRepository.findAll();
    	
    	for(Products product:allProducts) {
    		System.out.println(product);
    		product.getBuyers().forEach(System.out::println);
    	}

*/
    	

	}

}
