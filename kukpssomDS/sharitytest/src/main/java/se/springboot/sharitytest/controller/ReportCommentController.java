package se.springboot.sharitytest.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import se.springboot.sharitytest.model.Comment;
import se.springboot.sharitytest.model.ReportComment;
import se.springboot.sharitytest.model.UserReport;
import se.springboot.sharitytest.model.dto.ActivityDTO;
import se.springboot.sharitytest.model.dto.ReportCommentDTO;
import se.springboot.sharitytest.model.login.UserLogin;
import se.springboot.sharitytest.service.CommentService;
import se.springboot.sharitytest.service.ReportCommentService;

@Controller
public class ReportCommentController {
	
	@Autowired
	private ReportCommentService reportCommentService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "/reportcomment", method = RequestMethod.GET)
	public String reportCommentIndex(Model model) {
		
		ArrayList<ReportComment> reportComments = (ArrayList<ReportComment>) reportCommentService.findAll();
		model.addAttribute("reportComments",reportComments);
		
		return "reportcomment/index";
	}
	
	@RequestMapping(value = "/deletecomment", method = RequestMethod.GET)
	public String deleteComment(@RequestParam(value = "commentId") int commentId, Model model) {

		commentService.delete(commentId);
		
		return "redirect:/reportcomment";

	}
	
	@RequestMapping(value = "/commentreport", method = RequestMethod.GET)
	public String commentReport(Model model,@RequestParam("commentId")int commentId) {
		
		Comment comment = commentService.findById(commentId);
		
		model.addAttribute("comment", comment);
		
		return "reportcomment/reportform";
	}
	
	@RequestMapping(value = "/addreportcomment",method = RequestMethod.POST)
	public String addReportComment(Model model,@ModelAttribute("reportCommentDTO") ReportCommentDTO reportCommentDTO) {
		ReportComment reportComment = new ReportComment();
		Comment comment = commentService.findById(reportCommentDTO.getCommentId());
		
		reportComment.setComment(comment);
		reportComment.setDetail(reportCommentDTO.getDetail());
		reportComment.setUser(UserLogin.userLogin);
		
		reportCommentService.save(reportComment);
		
		return "redirect:/activitydetail" + "?activityId=" + comment.getActivity().getActivityId();
		
	}
	
	
}
