"use strict";

const url = "http://localhost:3000";

// Alert
function showAlert(message, labelStyle) {
  alertDiv.style.display = "block";
  alertDiv.innerHTML = message;

  let alertClass = `alert-${labelStyle}`;

  alertDiv.classList.add(alertClass);

  setTimeout(function () {
    hideAlert(alertClass);
  }, 3000);
}

function hideAlert(alertClass) {
  alertDiv.style.display = "none";
  alertDiv.innerHTML = "";
  alertDiv.classList.remove(alertClass);
}
