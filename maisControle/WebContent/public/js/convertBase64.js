function enviar(){
	
	$("#txtSenha").val(btoa($("#txtSenha").val()));
	var senha=$("#txtSenha").val();
	$("#Login").submit();
		
};
