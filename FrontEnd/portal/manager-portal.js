"use strict";

//NOTE: PJS mark indicates that variable or function is created in portal.js file

//Saving elements to vars
let dasboardDiv = document.getElementById("dashboardDiv");
let requestsDiv = document.getElementById("requestsDiv");

let dashboardButton = document.getElementById("dashboardButton");
let requestsButton = document.getElementById("requestsButton");

//Setting up event listeners
document.addEventListener("DOMContentLoaded", setupLoadedPage);

dashboardButton.addEventListener("click", function () {
  offcanvasButtonPressed(dashboardButton);
});

requestsButton.addEventListener("click", function () {
  offcanvasButtonPressed(requestsButton);
});

/* MARK: - Page Setup -------------------------------------------------------------------------------*/
function setupLoadedPage() {
  getUser(); //PJS
  dashboardButtonPressed();

  //TEMPORARY CODE
  requestsButtonPressed();
}

/* MARK: - Handling Navigation -----------------------------------------------------------------------*/
function offcanvasButtonPressed(button) {
  dashboardButton.classList.remove("pressed-button");
  requestsButton.classList.remove("pressed-button");

  button.classList.add("pressed-button");

  switch (button) {
    case dashboardButton:
      dashboardButtonPressed();
      break;
    case requestsButton:
      requestsButtonPressed();
      break;
  }
}

function dashboardButtonPressed() {
  requestsDiv.style.display = "none";
  dasboardDiv.style.display = "block";

  mainHeader.innerHTML = "Dashboard"; //PJS
}

function requestsButtonPressed() {
  dasboardDiv.style.display = "none";
  requestsDiv.style.display = "block";

  mainHeader.innerHTML = "Requests"; //PJS

  loadReimbsTable(); //PJS
}
