"use strict";

// NOTE: SJS* mark indicates that variable or function is created in subsequent .js files, e.g. manager-portal.js or employee-portal.js

// User ID variable that is populated in Page Setup section
let userId;
let userRoleId;
let activeReimbId; // Gets populated in loadReimbCard to allow manager manipulate reimb.

/* MARK: - Saving elements to vars ---------------------------------------------------------------*/
let navbarUserName = document.getElementById("navbarUserName");
let mainHeader = document.getElementById("mainHeader");

// Divs
let requestCardDiv = document.getElementById("requestCardDiv");

// Reimb Card elements
let receiptImageImg = document.getElementById("receiptImageImg");
let reimbCardIdSpan = document.getElementById("reimbCardIdSpan");
let reimbCardDescriptionP = document.getElementById("reimbCardDescriptionP");
let reimbCardAmountSpan = document.getElementById("reimbCardAmountSpan");
let reimbCardTypeSpan = document.getElementById("reimbCardTypeSpan");
let reimbCardSubmittedSpan = document.getElementById("reimbCardSubmittedSpan");
let reimbCardResolvedSpan = document.getElementById("reimbCardResolvedSpan");
let reimbCardResolverSpan = document.getElementById("reimbCardResolverSpan");
let reimbCardAuthorSpan = document.getElementById("reimbCardAuthorSpan");
let reimbCardStatusSpan = document.getElementById("reimbCardStatusSpan");

let reimbCardAuthorLi = document.getElementById("reimbCardAuthorLi");

// Buttons
let reimbCardBackButton = document.getElementById("reimbCardBackButton");
let filterReimbsPendingButton = document.getElementById(
  "filterReimbsPendingButton"
);
let filterReimbsDeniedButton = document.getElementById(
  "filterReimbsDeniedButton"
);
let filterReimbsApprovedButton = document.getElementById(
  "filterReimbsApprovedButton"
);
let filterReimbsResetButton = document.getElementById(
  "filterReimbsResetButton"
);
let logoutButton = document.getElementById("logoutButton");

/* MARK: - Adding Event Listeners ------------------------------------------------------------------*/
dashboardButton.addEventListener("click", function () {
  offcanvasButtonPressed(dashboardButton); //SJS*
});

reimbCardBackButton.addEventListener("click", function () {
  activeReimbId = 0;
  reimbsButtonPressed(); //SJS*
});

// Reimb table filter buttons
filterReimbsPendingButton.addEventListener("click", function () {
  reimbsButtonPressed(1); //SJS*
});

filterReimbsDeniedButton.addEventListener("click", function () {
  reimbsButtonPressed(2); //SJS*
});

filterReimbsApprovedButton.addEventListener("click", function () {
  reimbsButtonPressed(3); //SJS*
});

filterReimbsResetButton.addEventListener("click", function () {
  reimbsButtonPressed(); //SJS*
});

logoutButton.addEventListener("click", function (){
  logoutButtonPressed();
});

/* MARK: - Page Setup -------------------------------------------------------------------------------*/
async function getUser() {
  userId = getCookie("userId");
  userRoleId = getCookie("userRoleId");
  let response = await fetch(url + `/users/${userId}`, {
    // credentials: "include"
  });
  setUserName(response);
}

async function setUserName(response) {
  if (response.status >= 200 && response.status < 300) {
    let data = await response.json();
    navbarUserName.innerHTML = `Hi, ${data.user_first_name}`;
    // mainHeader.innerText = `Welcome ${data.user_first_name}`;
  } else {
    // IMPLEMENT ELSE
  }
}

/* MARK: - Reimbursements Table -----------------------------------------------------------------------*/
// Creates different thead depending on user role and iterates through data to populate tbody
async function loadReimbsTable(filter) {
  // Removing any previous thead and tbody
  while (reimbsTable.childElementCount > 0) {
    reimbsTable.removeChild(reimbsTable.lastChild);
  }

  // Creating a tbody that will be appended a child at the end of this function
  let reimbsTableHead = document.createElement("thead");
  let reimbsTableBody = document.createElement("tbody");

  // Sending fetch request to back-end
  let response = await fetch(url + "/reimbs", {
    // credentials: "include"
  });

  if (response.status >= 200 && response.status < 300) {
    let data = await response.json();

    reimbsTableHead = createTableHead();

    for (let reimb of data) {
      // If filter is passed this statement will skip iteration if not matching filter: 1-Pending, 2-Denied, 3-Approved
      if (filter) {
        if (reimb.reimb_status_id_fk != filter) {
          continue;
        }
      }

      // Creating a row setting onClick attribute
      let rowAnchor = document.createElement("a");
      let bodyRow = document.createElement("tr");
      // Setting onClick for the row, tableRowPressed is SJS*
      bodyRow.setAttribute("onClick", `tableRowPressed(${reimb.reimb_id})`);

      // Creating cells that will be appended to the row
      let requestIdCell = document.createElement("td");
      let amountCell = document.createElement("td");
      let typeCell = document.createElement("td");
      let descriptionCell = document.createElement("td");
      let submittedCell = document.createElement("td");
      let resolvedCell = document.createElement("td");
      let resolverCell = document.createElement("td");
      let authorCell = document.createElement("td");
      let statusCell = document.createElement("td");

      // Populating cells

      requestIdCell.innerHTML = reimb.reimb_id;
      amountCell.innerHTML = reimb.reimb_amount;
      typeCell.innerHTML = reimb.reimbType.reimb_type_name;
      descriptionCell.innerHTML = reimb.reimb_description;
      submittedCell.innerHTML = reimb.reimb_submitted;
      resolvedCell.innerHTML = reimb.reimb_resolved
        ? reimb.reimb_resolved
        : "Pending";
      resolverCell.innerHTML = reimb.reimb_resolver_id_fk
        ? reimb.reimbResolver.user_first_name
        : "Pending";
      authorCell.innerHTML = reimb.reimbAuthor.user_first_name;
      statusCell.innerHTML = reimb.reimbStatus.reimb_status_name;

      // Appendning cells to the row
      bodyRow.appendChild(requestIdCell);
      bodyRow.appendChild(amountCell);
      bodyRow.appendChild(typeCell);
      bodyRow.appendChild(descriptionCell);
      bodyRow.appendChild(submittedCell);
      bodyRow.appendChild(resolvedCell);
      bodyRow.appendChild(resolverCell);
      if (userRoleId == 1) {
        // If manager also append employee name
        bodyRow.appendChild(authorCell);
      }
      bodyRow.appendChild(statusCell);

      // Appending the row to the table body
      reimbsTableBody.appendChild(bodyRow);
    }

    // Appending the table head and body to the table
    reimbsTable.appendChild(reimbsTableHead);
    reimbsTable.appendChild(reimbsTableBody);
  } else {
    // IMPLEMENT ELSE
  }
}

// Returns a complete thead
function createTableHead() {
  // Creating thead that will be appended a child at the end of this function
  let reimbsTableHead = document.createElement("thead");

  // Creating table head
  let headRow = document.createElement("tr");
  let requestIdHead = document.createElement("th");
  let amountHead = document.createElement("th");
  let typeHead = document.createElement("th");
  let descriptionHead = document.createElement("th");
  let submittedHead = document.createElement("th");
  let reoslvedHead = document.createElement("th");
  let resolverHead = document.createElement("th");
  let authorHead = document.createElement("th"); // Only added if user is manager
  let statusHead = document.createElement("th");
  // ADD receipt pictre?

  // Populating head cells
  requestIdHead.innerHTML = "ID";
  amountHead.innerHTML = "Amount";
  typeHead.innerHTML = "Type";
  descriptionHead.innerHTML = "Description";
  submittedHead.innerHTML = "Submitted";
  reoslvedHead.innerHTML = "Resolved";
  resolverHead.innerHTML = "Resolver";
  authorHead.innerHTML = "Employee";
  statusHead.innerHTML = "Status";

  // Appending cells to the row
  headRow.appendChild(requestIdHead);
  headRow.appendChild(amountHead);
  headRow.appendChild(typeHead);
  headRow.appendChild(descriptionHead);
  headRow.appendChild(submittedHead);
  headRow.appendChild(reoslvedHead);
  headRow.appendChild(resolverHead);
  if (userRoleId == 1) {
    // If manager also append employee name
    headRow.appendChild(authorHead);
  }
  headRow.appendChild(statusHead);

  // Append the row to table head
  reimbsTableHead.appendChild(headRow);

  return reimbsTableHead;
}

/* MARK: - Reimbursement Card -----------------------------------------------------------------------*/
// This function is called in SJS* when user clicks
async function loadReimbCard(reimbId) {
  // Sending fetch request to back-end
  let response = await fetch(url + `/reimbs/${reimbId}`, {
    // credentials: "include"
  });

  if (response.status >= 200 && response.status < 300) {
    let data = await response.json();

    activeReimbId = data.reimb_id;

    // Populating elements with data
    receiptImageImg.src = data.reimb_receipt_url
    reimbCardIdSpan.innerHTML = data.reimb_id;
    reimbCardDescriptionP.innerHTML = data.reimb_description;
    reimbCardAmountSpan.innerHTML = data.reimb_amount;
    reimbCardTypeSpan.innerHTML = data.reimbType.reimb_type_name;
    reimbCardSubmittedSpan.innerHTML = data.reimb_submitted;
    reimbCardResolvedSpan.innerHTML = data.reimb_resolved
      ? data.reimb_resolved
      : "Pending";
    reimbCardResolverSpan.innerHTML = data.reimb_resolver_id_fk
      ? data.reimbResolver.user_first_name
      : "Pending";
    reimbCardAuthorSpan.innerHTML = data.reimbAuthor.user_first_name;
    reimbCardStatusSpan.innerHTML = data.reimbStatus.reimb_status_name;

    // If user is not admin the author (Employee) field is hidden
    if (userRoleId != 1) {
      reimbCardAuthorLi.classList.remove("d-flex");
      reimbCardAuthorLi.style.display = "none";
    }

    // Div visibility toggled here to avoid it loading before the above code completes
    requestCardDiv.style.display = "block";
  } else {
    // IMPLEMENT ELSE
  }
}

/* MARK: - Logout ------------------------------------------------------------------------------------*/
async function logoutButtonPressed() {

  let response = await fetch(url + "/logout", {
    method: "POST",
  }); //include

  if (response.status >= 200 && response.status < 300) {
    takeToLoginPage();
  } else {
    alert("Logout failed");
  }

}

async function takeToLoginPage() {
  window.location.href = "/login/login.html";
}

/* MARK: - Miscellaneous -----------------------------------------------------------------------------*/
// Function to get cookie value by name
function getCookie(name) {
  // Split cookie string and get all individual name=value pairs in an array
  var cookieArr = document.cookie.split(";");

  // Loop through the array elements
  for (var i = 0; i < cookieArr.length; i++) {
    var cookiePair = cookieArr[i].split("=");

    /* Removing whitespace at the beginning of the cookie name
          and compare it with the given string */
    if (name == cookiePair[0].trim()) {
      // Decode the cookie value and return
      return decodeURIComponent(cookiePair[1]);
    }
  }
  // Return null if not found
  return null;
}
