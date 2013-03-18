package com.excilys.servlet;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.excilys.bean.Company;
import com.excilys.bean.ListComputer;
import com.excilys.form.ComputerForm;
import com.excilys.service.ICompanyService;
import com.excilys.service.IComputerService;

@Controller
public class Computers {
	private final int NB_EL_PAGE = 10;
	
	@Autowired
	private IComputerService computerService;
	@Autowired
	private ICompanyService companyService;

	private final static Logger logger = LoggerFactory
			.getLogger(IComputerService.class);

	@RequestMapping(value = "/Computers", method = RequestMethod.GET)
	public String test(@RequestParam(value = "p", required = false) String p,
			@RequestParam(value = "s", required = false) String s,
			@RequestParam(value = "f", required = false) String f,
			@RequestParam(value = "searchC", required = false) String searchC,Model model) {

		logger.info("Entering Service.getComputer");
		int numPage = 0;
		try {
			numPage = Integer.parseInt(p);
		} catch (Exception e) {
		}

		double variableDouble = 0;
		if (s != null && s != "") {
			variableDouble = Double.parseDouble((String) s);
		}

		ListComputer liste = null;
		if ((f != null && f != "") || (searchC != null && searchC != "")) {
			String computerRecherche = f;
			liste = computerService.getComputers(computerRecherche,searchC,generatePageable(variableDouble,numPage));
		} else {
			liste = computerService.getComputers(generatePageable(variableDouble,numPage));
		}
		model.addAttribute("computer", liste.getListeComputer());
		model.addAttribute("nbel", liste.getSize());
		model.addAttribute("numpage", numPage);
		return "Computer";
	}

	@RequestMapping(value = "/ComputerId", method = RequestMethod.GET)
	public String computerId(@ModelAttribute("computer") ComputerForm computer,
			@RequestParam(value = "id", required = false) String id, Model model) {
		if (id == null) {
			model.addAttribute("company", companyService.getCompany());
		} else {
			Assert.isTrue(computerService.existComputer(Integer.parseInt(id)));
			model.addAttribute(
					"computer",
					new ComputerForm(computerService.getComputerById(Integer
							.parseInt(id))));
			model.addAttribute("company", companyService.getCompany());
		}
		return "InfoComputer";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable(value = "id") String id, Model model,
			RedirectAttributes redirectAttributes) {
		logger.info("Entering Service.deleteComputer");
		try {
			Assert.notNull(id);
			Assert.isTrue(computerService.existComputer(Integer.parseInt(id)));
			computerService.deleteComputer(Integer.parseInt((String) id));
			redirectAttributes.addFlashAttribute("delete", true);
			return "redirect:../Computers.html";
		} catch (IllegalArgumentException e) {
			logger.warn("WARNING: iae in Service.deleteComputer: "
					+ e.getMessage());
			return "redirect:../Computers.html";
		}
	}

	@RequestMapping(value = "/SaveComputer", method = RequestMethod.POST)
	public String saveComputer(
			@RequestParam(value = "id", required = false) String id,
			@Valid @ModelAttribute("computer") ComputerForm computer,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		if (id != null && !id.equals("0")) {
			Assert.isTrue(computerService.existComputer(Integer.parseInt(id)));
		}

		model.addAttribute("companies", companyService.getCompany());
		model.addAttribute("result", result);

		// if (result.hasErrors())
		// System.out.println("ERRORS");
		// else
		// System.out.println("NO ERRORS");

		// Toujours l'erreur sur les Company
		if (result.getErrorCount() > 0) {
			model.addAttribute("company", companyService.getCompany());
			return "InfoComputer";
		} else {
			Company comp = null;
			if (computer.getCompany() != null) {
				comp = companyService.getCompanyByID((int) computer
						.getCompany());
			}
			if (id != null && id != "" && !id.equals("0")) {
				computer.setIdComputer(Integer.parseInt(id));
				computerService
						.updateComputer(computer.convertToComputer(comp));
			} else {
				computerService.addComputer(computer
						.convertToComputerToAdd(comp));
			}
			redirectAttributes.addFlashAttribute("name",
					computer.getNameComputer());
			redirectAttributes.addFlashAttribute("update", true);
			return "redirect:Computers.html";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//-------------------Creation du pageable--------------------------//
	private Pageable generatePageable(double variableDouble,int numPage) {
		String name = orderByName(variableDouble);
		Order o = orderByOrder(variableDouble, name);
		return (Pageable) new PageRequest(numPage, NB_EL_PAGE, new Sort(o));
	}
	
	private Order orderByOrder(double s, String name) {
		// String name;
		Order o = new Order(Direction.ASC, name);
		if (s < 0) {
			o = new Order(Direction.DESC, name);
		}
		return o;
	}

	private String orderByName(double s) {
		String name = "";
		try {
			int sprime = (int) s;
			if (s < 0)
				sprime *= -1;
			switch (sprime) {
			case 1:
				name = "computer.nameComputer";
				break;
			case 2:
				name = "computer.introducedDate";
				break;
			case 3:
				name = "computer.dscountedDate";
				break;
			case 4:
				name = "company.nameCompany";
				break;
			default:
				name = "computer.nameComputer";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
}