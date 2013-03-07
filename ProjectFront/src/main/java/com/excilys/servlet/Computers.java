package com.excilys.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;
import com.excilys.service.ICompanyService;
import com.excilys.service.IComputerService;

@Controller
public class Computers{
	
	@Autowired
	private IComputerService computerService;
	@Autowired
	private ICompanyService companyService;

    @RequestMapping(value="/Computers", method = RequestMethod.GET)
    public String test(@RequestParam(value="p", required=false) String p,
    		@RequestParam(value="s", required=false) String s,
    		@RequestParam(value="f", required=false) String f,
    		@RequestParam(value="name", required=false) String name,
    		@RequestParam(value="delete", required=false) String delete,
    		Model model){
    
    	int numPage = 0;
		try {
			numPage = Integer.parseInt(p);
		} catch (Exception e) {
		}		
		
		double variableDouble = 0;
		if (s != null){
			variableDouble = Double.parseDouble((String) s);			
		}
		
		ListComputer liste = null;
		if (f != null){
			String computerRecherche = f;
			liste = computerService.getComputers(computerRecherche,numPage*10,variableDouble);			
		}else{
			liste = computerService.getComputers(numPage*10,variableDouble);
		}
		model.addAttribute("computer", liste.getListeComputer());
		model.addAttribute("nbel",liste.getSize());
		model.addAttribute("numpage",numPage);
		
		if (name != null){
			model.addAttribute("message", 2);
			model.addAttribute("name", name);	
		}
		
		if (delete != null){
			model.addAttribute("message", 1);
		}
		return "Computer";
    }
    

    @RequestMapping(value="/ComputerId", method = RequestMethod.GET)
    public String computerId(@RequestParam(value="id", required=false) String id,
        		Model model){
    	if(id == null){    		
    		model.addAttribute("company", companyService.getCompany());
    	}else{		
    		model.addAttribute("computer", computerService.getComputerById(Integer.parseInt(id)));
    		model.addAttribute("company", companyService.getCompany());
    	}
    	return "InfoComputer";
    }

    
    @RequestMapping(value="/Delete", method = RequestMethod.POST)
    public String delete(@RequestParam(value="id", required=true) String id,
        		Model model){
		computerService.deleteComputer(Integer.parseInt((String)id));
		return "redirect:Computers.html?delete=true";
    }
    
    @RequestMapping(value="/SaveComputer", method = RequestMethod.GET)
    public String saveComputer(@RequestParam(value="id", required=false) String id,
    		@RequestParam(value="company", required=false) String company,
    		@RequestParam(value="introduced", required=false) String introduced,
    		@RequestParam(value="discontinued", required=false) String discontinued,
    		@RequestParam(value="name", required=false) String name,
        		Model model){
    	
		Computer c = new Computer();

		if(id != null && id!=""){
			c.setIdComputer(Integer.parseInt((String) id));
		}
		if(!company.equals("")){
			c.setCompany(companyService.getCompanyByID(Integer
				.parseInt((String) company)));
		}else{
			c.setCompany(null);
		}
		
		boolean erreur = false;

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		if(introduced.equals(null) || introduced.equals(""))
			c.setIntroducedDate(null);
		else{
			try {
				Date d = simpleDateFormat.parse(introduced);
				c.setIntroducedDate(d);
			} catch (Exception e) {
				erreur = true;
				model.addAttribute("introducedError", 1);
				model.addAttribute("introducedValue",
						introduced);
//				e.printStackTrace();
			}
		}
		
		if(discontinued.equals(null) || discontinued.equals(""))
			c.setDscountedDate(null);
		else{
			try {
				Date d = simpleDateFormat.parse(discontinued);
				c.setDscountedDate(d);
			} catch (Exception e) {
				erreur = true;
				model.addAttribute("discontinuedError", 1);
				model.addAttribute("discontinuedValue",
						discontinued);
//				e.printStackTrace();
			}
		}

		c.setNameComputer(name);
		if (erreur) {
			model.addAttribute("computer", c);
			model.addAttribute("company", companyService.getCompany());
	    	return "InfoComputer";
		} else {
			if(id != null && id!=""){
				computerService.updateComputer(c);
			}else{
				computerService.addComputer(c);
			}
			model.addAttribute("name",c.getNameComputer());
			return "redirect:Computers.html";
		}
    }
}