<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="pas_con none" id="changepwd">
	<p>
		<span><s>*</s>当前密码:</span>
		<input id="currentPas" type="password" maxlength="16">
	</p>
	<p>
		<span><s>*</s>新&ensp;密&ensp;码:</span>
		<input id="newPas" type="password" maxlength="16">
	</p>
	<p>
		<span><s>*</s>确认密码:</span>
		<input id="confirmPas" type="password" maxlength="16">
	</p>
	<p>
		<span></span>
		<b id="text"></b>
	</p>
	<p>
		<span></span>
		<button id="submit" onclick="changePassword()">提交</button>
	</p>
</div>