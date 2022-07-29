"use strict";

//NOTE: PJS* mark indicates that variable or function is created in portal.js file

//Saving elements to vars
let dasboardDiv = document.getElementById("dashboardDiv");
let requestsDiv = document.getElementById("requestsDiv");

let reimbsButton = document.getElementById("reimbsButton");
let requestCardDiv = document.getElementById("requestCardDiv");
let reimbDenyButton = document.getElementById("reimbDenyButton");
let reimbApproveButton = document.getElementById("reimbApproveButton");

//Setting up event listeners
document.addEventListener("DOMContentLoaded", setupLoadedPage);

dashboardButton.addEventListener("click", function () {
  offcanvasButtonPressed(dashboardButton);
});

reimbsButton.addEventListener("click", function () {
  offcanvasButtonPressed(reimbsButton);
});

/* MARK: - Page Setup -------------------------------------------------------------------------------*/
function setupLoadedPage() {
  getUser(); //PJS*
  dashboardButtonPressed();

  //TEMPORARY CODE
  reimbsButtonPressed();
}

/* MARK: - Handling Navigation -----------------------------------------------------------------------*/
function offcanvasButtonPressed(button) {
  dashboardButton.classList.remove("pressed-button");
  reimbsButton.classList.remove("pressed-button");

  button.classList.add("pressed-button");

  switch (button) {
    case dashboardButton:
      dashboardButtonPressed();
      break;
    case reimbsButton:
      reimbsButtonPressed();
      break;
  }
}

function dashboardButtonPressed() {
  requestsDiv.style.display = "none";
  requestCardDiv.style.display = "none";
  dasboardDiv.style.display = "block";

  mainHeader.innerHTML = "Dashboard"; //PJS*
}

function reimbsButtonPressed() {
  dasboardDiv.style.display = "none";
  requestCardDiv.style.display = "none";
  requestsDiv.style.display = "block";

  mainHeader.innerHTML = "Requests"; //PJS*

  loadReimbsTable(); //PJS*
}

//This function is called via onClick attribute in HTML
function tableRowPressed(reimbId) {
  dasboardDiv.style.display = "none";
  requestsDiv.style.display = "none";
  requestCardDiv.style.display = "block";
  
  loadReimbCard(reimbId); //PJS*
}
