const rmCheck = document.getElementById("rememberme"), id = document
		.getElementById("ID");

if (localStorage.checkbox && localStorage.checkbox !== "") {
	rmCheck.setAttribute("checked", "checked");
	id.value = localStorage.username;
} else {
	rmCheck.removeAttribute("checked");
	emailInput.value = "";
}

function lsRememberMe() {
	if (rmCheck.checked && emailInput.value !== "") {
		localStorage.username = id.value;
		localStorage.checkbox = rmCheck.value;
	} else {
		localStorage.username = "";
		localStorage.checkbox = "";
	}
}