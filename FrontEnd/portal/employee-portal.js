"use strict";

//NOTE: PJS mark indicates that variable or function is created in portal.js file

//Saving elements to vars
let dasboardDiv = document.getElementById("dashboardDiv");
let newExpenseDiv = document.getElementById("newExpenseDiv");
let reimbsDiv = document.getElementById("reimbsDiv");

let reimbsTable = document.getElementById("reimbsTable");

let dashboardButton = document.getElementById("dashboardButton");
let newExpenseButton = document.getElementById("newExpenseButton");
let reimbsButton = document.getElementById("reimbsButton");
let submitExpenseButton = document.getElementById("submitExpenseButton");

let expenseAmountInput = document.getElementById("expenseAmountInput");
let expenseDescriptionInput = document.getElementById(
  "expenseDescriptionInput"
);
let expenseTypeSelector = document.getElementById("expenseTypeSelector");

//Setting up event listeners
document.addEventListener("DOMContentLoaded", setupLoadedPage);

dashboardButton.addEventListener("click", function () {
  offcanvasButtonPressed(dashboardButton);
});
newExpenseButton.addEventListener("click", function () {
  offcanvasButtonPressed(newExpenseButton);
});
reimbsButton.addEventListener("click", function () {
  offcanvasButtonPressed(reimbsButton);
});
submitExpenseButton.onclick = submitExpenseButtonPressed;

/* MARK: - Page Setup -------------------------------------------------------------------------------*/
function setupLoadedPage() {
  getUser(); //PJS

  dashboardButtonPressed();
  //TEMPORARY CODE
  newExpenseButtonPressed();
}

/* MARK: - Handling Navigation -----------------------------------------------------------------------*/
function offcanvasButtonPressed(button) {
  dashboardButton.classList.remove("pressed-button");
  newExpenseButton.classList.remove("pressed-button");
  reimbsButton.classList.remove("pressed-button");

  button.classList.add("pressed-button");

  switch (button) {
    case dashboardButton:
      dashboardButtonPressed();
      break;
    case newExpenseButton:
      newExpenseButtonPressed();
      break;
    case reimbsButton:
      reimbsButtonPressed();
      break;
  }
}

function dashboardButtonPressed() {
  newExpenseDiv.style.display = "none";
  reimbsDiv.style.display = "none";
  dasboardDiv.style.display = "block";

  mainHeader.innerHTML = "Dashboard"; //PJS
}

function newExpenseButtonPressed() {
  dasboardDiv.style.display = "none";
  reimbsDiv.style.display = "none";
  newExpenseDiv.style.display = "block";

  mainHeader.innerHTML = "New Expense"; //PJS
}

function reimbsButtonPressed() {
  dasboardDiv.style.display = "none";
  newExpenseDiv.style.display = "none";
  reimbsDiv.style.display = "block";

  mainHeader.innerHTML = "Reimbursements"; //PJS

  loadReimbs();
}

/* MARK: - New Expense -------------------------------------------------------------------------------*/
async function submitExpenseButtonPressed() {

  let expenseAmount = expenseAmountInput.value;
  let expenseDescription = expenseDescriptionInput.value;
  let expenseTypeIndex = expenseTypeSelector.value;

  console.log(
    expenseAmount + " " + expenseDescription + " " + expenseTypeIndex
  );

  let newExpenseJson = JSON.stringify({
    reimb_amount: expenseAmount,
    reimb_description: expenseDescription,
    reimb_receipt_url: "url_here",
    reimb_type_id_fk: expenseTypeIndex,
    reimb_author_id_fk: userId
    // reimb_resolver_id_fk: 1,
  });

  let response = await fetch(url + "/reimbs", {
    method: "POST",
    body: newExpenseJson,
    // credentials: "include"
  });

  if (response.status >= 200 && response.status < 300) {
    console.log("success");
  } else {
    console.log("messup");
  }
}

/* MARK: - Reimbursements ----------------------------------------------------------------------------*/
async function loadReimbs() {
  // Removing any previous tbody
  if (reimbsTable.childElementCount > 1) {
    reimbsTable.removeChild(reimbsTable.lastChild);
  }

  // Creating a tbody that will be appended a child at the end of this function
  let reimbsTableBody = document.createElement("tbody");

  // Sending fetch request to back-end
  let response = await fetch(url + "/reimbs", {
    // credentials: "include"
  });

  if (response.status >= 200 && response.status < 300) {
    let data = await response.json();

    for (let reimb of data) {
      // Creating a row and cells for every data
      let row = document.createElement("tr");
      let amountCell = document.createElement("td");
      let typeCell = document.createElement("td");
      let descriptionCell = document.createElement("td");
      let submittedCell = document.createElement("td");
      let statusCell = document.createElement("td");

      // Populating cells
      amountCell.innerHTML = reimb.reimb_amount;
      typeCell.innerHTML = reimb.reimbType.reimb_type_name;
      descriptionCell.innerHTML = reimb.reimb_description;
      submittedCell.innerHTML = reimb.reimb_submitted;
      statusCell.innerHTML = reimb.reimb_is_approved;

      // Appendning cells to the row
      row.appendChild(amountCell);
      row.appendChild(typeCell);
      row.appendChild(descriptionCell);
      row.appendChild(submittedCell);
      row.appendChild(statusCell);

      // Appending the row to the table body
      reimbsTableBody.appendChild(row);
    }

    // Appending the table body to the table
    reimbsTable.appendChild(reimbsTableBody);
  }
}
