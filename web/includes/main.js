// Dynamic form changes to the layout of the website is done using
// jQuery event handlers and effects in the event.js file

//separate functions for form validation of each individual input

function validateEmail(){
  if(document.getElementById("email").value.includes("'")){
    document.getElementById("email_error").className="isVisible";
    document.getElementById("email").style.backgroundColor = '#fcf4a3';
    document.getElementById("errorMessage5").className = 'isVisible2';

  }
  else{
    document.getElementById("email_error").className="notVisible";
    document.getElementById("email").style.backgroundColor = 'white';
    document.getElementById("errorMessage5").className = 'notVisible';
  }
}

function validateFullname(){
  if(document.getElementById("fullName").value.includes("'")){
    document.getElementById("fullName_error").className="isVisible";
    document.getElementById("fullName").style.backgroundColor = '#fcf4a3';
    document.getElementById("errorMessage6").className = 'isVisible2';

  }
  else{
    document.getElementById("fullName_error").className="notVisible";
    document.getElementById("fullName").style.backgroundColor = 'white';
    document.getElementById("errorMessage6").className = 'notVisible';
  }

}

//Date of birth only takes numerical input therefore checking for single quotes is not needed

function validateUsername(){
  if(document.getElementById("Username").value.includes("'")){
    document.getElementById("username_error").className="isVisible";
    document.getElementById("Username").style.backgroundColor = '#fcf4a3';
    document.getElementById("errorMessage1").className = 'isVisible2';

  }
  else{
    document.getElementById("username_error").className="notVisible";
    document.getElementById("Username").style.backgroundColor = 'white';
    document.getElementById("errorMessage1").className = 'notVisible';
  }

}

function validatePassword(){
  if(document.getElementById("pass").value.includes("'")){
   document.getElementById("pass_error").className="isVisible";
   document.getElementById("errorMessage4").className = 'isVisible2';
   document.getElementById("pass").style.backgroundColor = '#fcf4a3';
 }
 else{
   document.getElementById("pass_error").className="notVisible";
   document.getElementById("pass").style.backgroundColor = 'white';
   document.getElementById("errorMessage4").className = 'notVisible';
  }

}

function checkPassword(){
  //check for uppercase, lowercase, and number
  var pass = document.getElementById("pass").value;
  var flag = 0;
  var up = 0, down = 0, num = 0;
  for(var i=0; i < pass.length; i++){
    if(!isNaN(pass.charAt(i)) && num != 1){
           num = 1;
       }
       else if(isNaN(pass.charAt(i)) && pass.charAt(i) == pass.charAt(i).toUpperCase() && up != 1){
           up = 1;
       }
       else if(isNaN(pass.charAt(i)) && pass.charAt(i) == pass.charAt(i).toLowerCase() && down != 1){
           down = 1;
       }
       if(up == 1 && down == 1 && num == 1){
           flag = 1;
           break;
       }
  }

 if(flag == 0){
    document.getElementById("pass_error").className = "isVisible";
    document.getElementById("pass").style.backgroundColor = '#fcf4a3';
    document.getElementById("invalidPass").className = "isVisible2";
    return false;

  }

  window.open('welcome.html'); // for some reason it does not open in the same tab

}

function validateConfirmPassword(){
    // valid passwords
    var password = document.getElementById('pass');
    var confirmPassword = document.getElementById('confirm');
    if(document.getElementById("pass").value == document.getElementById("confirm").value) {
        document.getElementById("pass").style.backgroundColor = 'white';
        document.getElementById("confirm").style.backgroundColor = 'white';
        document.getElementById("confirm_error").className = 'notVisible';
        document.getElementById("errorMessage2").className = 'notVisible';
        document.getElementById("invalidConfirm").className = 'notVisible';

    }

    else if(document.getElementById("confirm").value.includes("'")){
        document.getElementById("confirm_error").className="isVisible";
        document.getElementById("errorMessage2").className = 'isVisible2';
        document.getElementById("confirm").style.backgroundColor = '#fcf4a3';
      }

    else {
        document.getElementById("pass").style.backgroundColor = '#fcf4a3';
        document.getElementById("confirm").style.backgroundColor = '#fcf4a3';
        document.getElementById("confirm_error").className = 'isVisible';
        document.getElementById("invalidConfirm").className = 'isVisible2';

    }
}


function validateSecurity(){
  if(document.getElementById("Answer").value.includes("'")){
    document.getElementById("answer_error").className="isVisible";
    document.getElementById("Answer").style.backgroundColor = '#fcf4a3';
    document.getElementById("errorMessage3").className = 'isVisible2';
  }
  else{
    document.getElementById("answer_error").className="notVisible";
    document.getElementById("Answer").style.backgroundColor = 'white';
    document.getElementById("errorMessage3").className = 'notVisible';
  }

}


function validateForm(){

   // valid fullname
    var fullName1 = document.getElementById('fullName').value;      // get name value

    // split it at the 'space' and if the number of elements in the array
    // is less than 2, means there's only one word
    if(document.getElementById('fullName').value.split(' ').length < 2)
    {
        document.getElementById('errorMessage').className = 'isVisible';
        document.getElementById('fullName_error').className = 'isVisible'; // for the *

        document.getElementById("fullName").style.backgroundColor = '#fcf4a3'; // change color of background to yellow
        document.getElementById('invalidName').className = 'isVisible2';

        return false;


      //if there are no errors, open signup continue

      //window.open('signup-continue.html', '_self');
      //window.location.href = 'signup-continue.html';

     // window.open('signup-continue.html'); // for some reason it does not open in the same tab
     

    }
}
