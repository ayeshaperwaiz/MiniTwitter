//jQuery
//$(".signup-form").hide();
//$(".signup").css("background", "none");
$(".inputAnswer").hide();

$(".signup").click(function(){
  $(".signup-form").show();
  $(".login-form").hide();
  window.open("signup.jsp","_self");
  $(".inputShow").hide();
  $(".signup").css("background", "#fff");
  $(".login").css("background", "none");
});

$(".login").click(function(){
  $(".signup-form").hide();
  $(".login-form").show();
  $(".signup").css("background", "none");
  $(".login").css("background", "#fff");
});


//display answer field when a security question is selected
$("#SecurityQuestion").change(function(){
  $(".inputAnswer").show(); 
})

/*$(".btn").click(function(){
  $(".input").val("");
}); */

/*$(".btn1").click(function(){
  $(".signup-form").hide();
  $(".signup-continue").show();
});*/
