package com.excilys.servlet;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.bean.Company;
import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;
import com.excilys.form.ComputerForm;
import com.excilys.service.ICompanyService;
import com.excilys.service.IComputerService;

@Controller
public class Computers {

	@Autowired
	private IComputerService computerService;
	@Autowired
	private ICompanyService companyService;

	// @Autowired
	// private ComputerValidator computerValidator;

	@RequestMapping(value = "/Computers", method = RequestMethod.GET)
	public String test(@RequestParam(value = "p", required = false) String p,
			@RequestParam(value = "s", required = false) String s,
			@RequestParam(value = "f", required = false) String f,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "delete", required = false) String delete,
			Model model) {

		int numPage = 0;
		try {
			numPage = Integer.parseInt(p);
		} catch (Exception e) {
		}

		double variableDouble = 0;
		if (s != null) {
			variableDouble = Double.parseDouble((String) s);
		}

		ListComputer liste = null;
		if (f != null) {
			String computerRecherche = f;
			liste = computerService.getComputers(computerRecherche,
					numPage * 10, variableDouble);
		} else {
			liste = computerService.getComputers(numPage * 10, variableDouble);
		}
		model.addAttribute("computer", liste.getListeComputer());
		model.addAttribute("nbel", liste.getSize());
		model.addAttribute("numpage", numPage);

		if (name != null) {
			model.addAttribute("message", 2);
			model.addAttribute("name", name);
		}

		if (delete != null) {
			model.addAttribute("message", 1);
		}
		return "Computer";
	}

	@RequestMapping(value = "/ComputerId", method = RequestMethod.GET)
	public String computerId(@ModelAttribute("computer") ComputerForm computer,
			@RequestParam(value = "id", required = false) String id, Model model) {
		if (id == null) {
			model.addAttribute("company", companyService.getCompany());
		} else {
			model.addAttribute("computer",
					new ComputerForm(computerService.getComputerById(Integer.parseInt(id))));
			model.addAttribute("company", companyService.getCompany());
		}
		return "InfoComputer";
	}

	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public String delete(
			@RequestParam(value = "id", required = true) String id, Model model) {
		computerService.deleteComputer(Integer.parseInt((String) id));
		return "redirect:Computers.html?delete=true";
	}

	@RequestMapping(value = "/SaveComputer", method = RequestMethod.GET)
	public String saveComputer(
			@RequestParam(value="id", required=false) String id,
			@Valid @ModelAttribute("computer") ComputerForm computer,
			BindingResult result, Model model) {
		model.addAttribute("companies", companyService.getCompany());
		model.addAttribute("result", result);

		 if (result.hasErrors())
			 System.out.println("ERRORS");
		 else
			 System.out.println("NO ERRORS");
		

		// Toujours l'erreur sur les Company
		if (result.getErrorCount()>0) {
			model.addAttribute("company", companyService.getCompany());
			return "InfoComputer";
		} else {
			Company comp = null;
			if(computer.getCompany()!=null){
				comp = companyService.getCompanyByID((int)computer.getCompany());
			}
			if (id != null && id != "" && !id.equals("0")) {
				computer.setIdComputer(Integer.parseInt(id));
				computerService.updateComputer(computer.convertToComputer(comp));
			} else {
				computerService.addComputer(computer.convertToComputerToAdd(comp));
			}
			model.addAttribute("name", computer.getNameComputer());
			return "redirect:Computers.html";
		}

	}
}