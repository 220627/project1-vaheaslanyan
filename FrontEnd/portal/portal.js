"use strict";

//User ID variable that is populated in Page Setup section
let userId;
let userRoleId;

//Saving elements to vars
let navbarUserName = document.getElementById("navbarUserName");
let mainHeader = document.getElementById("mainHeader");

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
  }
}

/* MARK: - Reimbursements ----------------------------------------------------------------------------*/
// Creates different thead depending on user role and iterates through data to populate tbody 
async function loadReimbsTable() {
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
        // Creating a row and cells for every data
        let bodyRow = document.createElement("tr");
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
        resolvedCell.innerHTML = reimb.reimb_resolved ? reimb.reimb_resolved : "Pending";
        resolverCell.innerHTML = reimb.reimb_resolver_id_fk ? reimb.reimbResolver.user_first_name : "Pending";
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
        if (userRoleId == 1) { // If manager also append employee name
            bodyRow.appendChild(authorCell);
        }
        bodyRow.appendChild(statusCell);
  
        // Appending the row to the table body
        reimbsTableBody.appendChild(bodyRow);
      }
  
      // Appending the table head and body to the table
      reimbsTable.appendChild(reimbsTableHead);
      reimbsTable.appendChild(reimbsTableBody);
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
     if (userRoleId == 1) { // If manager also append employee name
       headRow.appendChild(authorHead);
     }
     headRow.appendChild(statusHead);
 
     // Append the row to table head
     reimbsTableHead.appendChild(headRow);

     return reimbsTableHead;
}


/* MARK: - Miscellaneous -----------------------------------------------------------------------------*/
//Function to get cookie value by name
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
