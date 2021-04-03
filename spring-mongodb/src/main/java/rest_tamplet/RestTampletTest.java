package rest_tamplet;

import java.util.Date;

import com.app.core.models.Category;
import com.app.core.models.Company;
import com.app.core.models.Coupon;
import com.app.core.models.Customer;

public class RestTampletTest {

	public static void main(String[] args) {

		try {
			Login login = new Login();
//			Admin admin = new Admin(login.adminLogin());
//			System.out.println("token: " + admin.getHeaders());
			// ****** Admin company methods *********
//			admin.getCompany(8L);
//			admin.getAllCompanies();
//			Company company = admin.getCompany(8L);
//			company.setEmail("111");
//			admin.updateCompany(company);
//			Company company = new Company("z1", "z1", "z1");
//			admin.addCompany(company);
//			admin.deleteCompany(13L);
//			company.setId(8L);
//			company.setEmail("rest_update");
//			admin.updateCompany(company);
			
			// ****** Admin customer methods *********
//			Customer customer = new Customer("rt2", "rt2", "rt2", "rt2");
//			admin.addCustomer(customer);
//			admin.getAllCustomers();
			//Customer customer = admin.getCustoemr(2L);
//			Customer customer = admin.getCustoemr(1L);
			//dmin.updateCustomer(customer);
//			admin.getCustoemr(1L);
//			admin.deleteCustomer(2L);
			
			// ****** Comapny methods ******** //
//			CompanyRest companyRest = new CompanyRest(login.companyLogin("t2", "t2"));	
//			System.out.println("token: " + companyRest.getHeaders());
//			Coupon coupon = new Coupon(Category.FOOD, "t_rt", "d_rt", new Date(2021, 1, 1), new Date(2021, 10, 2), 10, 50, "");
//			System.out.println(coupon.getCategory());
//			companyRest.addCoupon(coupon);
//			companyRest.getAllCoupons();
//			companyRest.getCompanyDetails();
//			companyRest.getCouponsPriceLessThen(100);
			
			// ***** Customer methods ******** //
			CustomerRest customerRest = new CustomerRest(login.customerLogin("rt2", "rt2"));
//			customerRest.getCustomerDetails();
//			customerRest.getAllCoupons();
//			customerRest.getCustomerCoupons();
			customerRest.getCouponsByCategory(Category.ELECTRICITY);
//			customerRest.purchaseCoupon("60685ed09a55e35541bb1c47");
//			login.customerLogin("www", "www");

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
