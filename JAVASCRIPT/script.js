document.addEventListener('DOMContentLoaded', () => {
    // Get DOM elements
    const phoneField = document.getElementById('phone');
    const eventTypeSelect = document.getElementById('eventType');
    const eventRegistrationForm = document.getElementById('eventRegistrationForm');
    const messageTextarea = document.getElementById('message'); // For event registration message
    const feedbackMessageTextarea = document.getElementById('feedbackMessage'); // For feedback message
    const imageGalleryImages = document.querySelectorAll('.gallery-item img'); // Select images within gallery items
    const promoVideo = document.getElementById('promoVideo');
    const preferredEventTypeSelect = document.getElementById('preferredEventType');
    const clearPreferencesButton = document.getElementById('clearPreferencesButton');
    const findNearbyEventsButton = document.getElementById('findNearbyEventsButton');

    // --- Event Listeners ---

    // onblur to validate a phone number field
    if (phoneField) {
        phoneField.addEventListener('blur', validatePhoneNumber);
    }

    // onchange on a dropdown to display the selected event fee
    if (eventTypeSelect) {
        eventTypeSelect.addEventListener('change', displayEventFee);
    }

    // onclick on a submit button to show a confirmation (handled by form submit)
    if (eventRegistrationForm) {
        eventRegistrationForm.addEventListener('submit', (event) => {
            event.preventDefault(); // Prevent default form submission
            showConfirmation();
        });
    }

    // ondblclick on an image to enlarge it
    if (imageGalleryImages.length > 0) {
        imageGalleryImages.forEach(img => {
            img.addEventListener('dblclick', enlargeImage);
        });
    }

    // Capture key events in the event registration message textarea and count characters
    if (messageTextarea) {
        messageTextarea.addEventListener('keyup', (event) => countCharacters(event, 'charCount'));
    }

    // Capture key events in the feedback textarea and count characters
    if (feedbackMessageTextarea) {
        feedbackMessageTextarea.addEventListener('keyup', (event) => countCharacters(event, 'feedbackCharCount'));
    }

    // --- Task 7: Video Invite with Media Events ---

    // Use oncanplay to display a message like "Video ready to play"
    if (promoVideo) {
        promoVideo.addEventListener('canplay', () => {
            const videoStatusElement = document.getElementById('videoStatus');
            if (videoStatusElement) {
                videoStatusElement.textContent = "Video ready to play";
                videoStatusElement.className = 'message-box bg-green-100 border border-green-400 text-green-700';
            }
        });
    }

    // --- Task 8: Saving User Preferences ---

    // Load saved preference on reload
    if (preferredEventTypeSelect) {
        const savedEventType = localStorage.getItem('preferredEventType');
        if (savedEventType) {
            preferredEventTypeSelect.value = savedEventType;
        }
        // Save selected event type in localStorage
        preferredEventTypeSelect.addEventListener('change', (event) => {
            localStorage.setItem('preferredEventType', event.target.value);
            console.log(`Preferred event type saved: ${event.target.value}`);
            displayCustomMessage(`Preferred event type saved: ${event.target.value}`, "info");
        });
    }

    // Add a "Clear Preferences" button that clears both localStorage and sessionStorage
    if (clearPreferencesButton) {
        clearPreferencesButton.addEventListener('click', clearPreferences);
    }

    // --- Task 9: Geolocation for Event Mapping ---

    // Create a button "Find Nearby Events" and attach click listener
    if (findNearbyEventsButton) {
        findNearbyEventsButton.addEventListener('click', findNearbyEvents);
    }
});

// Use onbeforeunload to warn users if they try to leave the form page unfinished
window.addEventListener('beforeunload', (event) => {
    const nameField = document.getElementById('name');
    if (nameField && nameField.value.trim() !== '') {
        const message = "You have unsaved changes. Are you sure you want to leave?";
        event.returnValue = message; // Standard for most browsers
        return message; // For older browsers
    }
});

/**
 * Validates the phone number field.
 */
function validatePhoneNumber() {
    const phoneField = document.getElementById('phone');
    const phoneError = document.getElementById('phoneError');
    // Simple pattern for XXX-XXX-XXXX or XXXXXXXXXX
    const phonePattern = /^(\d{3}-\d{3}-\d{4}|\d{10})$/;

    if (phoneField.value.trim() === '') {
        phoneError.textContent = ""; // Clear error if field is empty
        return true;
    }

    if (!phonePattern.test(phoneField.value)) {
        phoneError.textContent = "Please enter a valid phone number (e.g., 123-456-7890 or 1234567890).";
        return false;
    } else {
        phoneError.textContent = "";
        return true;
    }
}

/**
 * Displays the event fee based on the selected event type.
 */
function displayEventFee() {
    const selectElement = document.getElementById('eventType');
    const selectedOption = selectElement.options[selectElement.selectedIndex];
    const fee = selectedOption.getAttribute('data-fee') || '0'; // Default to 0 if no data-fee
    document.getElementById('eventFee').textContent = `Event Fee: $${fee}`;
}

/**
 * Displays a confirmation message after form submission.
 */
function showConfirmation() {
    const confirmationOutput = document.getElementById('confirmationOutput');
    // Ensure phone number is valid before showing confirmation
    if (!validatePhoneNumber()) {
        if (confirmationOutput) {
            confirmationOutput.textContent = "Please correct the phone number error before submitting.";
            confirmationOutput.className = 'message-box bg-red-100 border border-red-400 text-red-700';
        }
        return;
    }

    const name = document.getElementById('name')?.value;
    const email = document.getElementById('email')?.value;
    const eventTypeSelect = document.getElementById('eventType');
    const eventType = eventTypeSelect ? eventTypeSelect.value : '';

    if (name && email && eventType) {
        if (confirmationOutput) {
            confirmationOutput.textContent = `Thank you, ${name}! Your registration for the ${eventType} event has been submitted. A confirmation email will be sent to ${email}.`;
            confirmationOutput.className = 'message-box bg-green-100 border border-green-400 text-green-700';
        }
    } else {
        if (confirmationOutput) {
            confirmationOutput.textContent = "Please fill in all required fields.";
            confirmationOutput.className = 'message-box bg-red-100 border border-red-400 text-red-700';
        }
    }
    console.log("Form submitted, confirmation message displayed.");
}

/**
 * Toggles the enlargement of an image on double-click.
 * @param {Event} event The double-click event.
 */
function enlargeImage(event) {
    const img = event.target;
    img.classList.toggle('enlarged');
    // Optionally, if you want to prevent scrolling when enlarged:
    // document.body.style.overflow = img.classList.contains('enlarged') ? 'hidden' : '';
    console.log("Image double-clicked, toggling enlargement.");
}

/**
 * Counts characters in a textarea and updates the remaining count.
 * @param {Event} event The keyup event.
 * @param {string} charCountId The ID of the span element to update with the character count.
 */
function countCharacters(event, charCountId) {
    const textarea = event.target;
    const maxLength = parseInt(textarea.getAttribute('maxlength'));
    const currentLength = textarea.value.length;
    const charsLeft = maxLength - currentLength;
    const charCountElement = document.getElementById(charCountId);
    if (charCountElement) {
        charCountElement.textContent = Math.max(0, charsLeft); // Ensure it doesn't go below 0
    }
    console.log(`Characters typed: ${currentLength}, remaining: ${charsLeft} for ${charCountId}`);
}

/**
 * Clears user preferences from localStorage and sessionStorage.
 * Displays a custom message instead of alert().
 */
function clearPreferences() {
    localStorage.clear();
    sessionStorage.clear();
    const preferredEventTypeSelect = document.getElementById('preferredEventType');
    if (preferredEventTypeSelect) {
        preferredEventTypeSelect.value = ''; // Reset dropdown
    }
    displayCustomMessage("All preferences cleared!", "success");
    console.log("Local and session storage cleared.");
}

/**
 * Finds the user's current geolocation and displays coordinates.
 * Handles permission denial and timeouts.
 */
function findNearbyEvents() {
    const coordinatesElement = document.getElementById('coordinates');
    const geoErrorElement = document.getElementById('geoError');

    if (coordinatesElement) {
        coordinatesElement.textContent = "Attempting to find your location...";
        coordinatesElement.className = 'message-box bg-yellow-100 border border-yellow-400 text-yellow-700';
    }
    if (geoErrorElement) {
        geoErrorElement.textContent = "";
    }

    if (navigator.geolocation) {
        const options = {
            enableHighAccuracy: true,
            timeout: 10000, // Increased timeout for better accuracy
            maximumAge: 0
        };
        navigator.geolocation.getCurrentPosition(
            (position) => {
                const lat = position.coords.latitude;
                const lon = position.coords.longitude;
                if (coordinatesElement) {
                    coordinatesElement.textContent = `Latitude: ${lat}, Longitude: ${lon}`;
                    coordinatesElement.className = 'message-box bg-green-100 border border-green-400 text-green-700';
                }
                console.log(`Geolocation successful: Lat ${lat}, Lon ${lon}`);
            },
            (error) => {
                let errorMessage = "";
                switch (error.code) {
                    case error.PERMISSION_DENIED:
                        errorMessage = "User denied the request for Geolocation. Please allow location access in your browser settings.";
                        break;
                    case error.POSITION_UNAVAILABLE:
                        errorMessage = "Location information is unavailable. Try again later.";
                        break;
                    case error.TIMEOUT:
                        errorMessage = "The request to get user location timed out. Please check your internet connection or try again.";
                        break;
                    case error.UNKNOWN_ERROR:
                        errorMessage = "An unknown error occurred while trying to get your location.";
                        break;
                }
                if (geoErrorElement) {
                    geoErrorElement.textContent = `Error: ${errorMessage}`;
                    geoErrorElement.className = 'message-box bg-red-100 border border-red-400 text-red-700';
                }
                if (coordinatesElement) {
                    coordinatesElement.textContent = ""; // Clear coordinates on error
                }
                console.error(`Geolocation error: ${errorMessage}`);
            },
            options
        );
    } else {
        if (geoErrorElement) {
            geoErrorElement.textContent = "Geolocation is not supported by this browser. Please update your browser.";
            geoErrorElement.className = 'message-box bg-red-100 border border-red-400 text-red-700';
        }
        console.error("Geolocation not supported.");
    }
}

/**
 * Displays a custom message in a temporary modal-like fashion.
 * @param {string} message The message to display.
 * @param {string} type The type of message (e.g., 'success', 'error', 'info').
 */
function displayCustomMessage(message, type) {
    let messageBox = document.getElementById('customMessageBox');
    if (!messageBox) {
        messageBox = document.createElement('div');
        messageBox.id = 'customMessageBox';
        // Initial state for transition
        messageBox.className = 'fixed top-5 left-1/2 -translate-x-1/2 p-4 rounded-lg shadow-lg z-50 transition-all duration-300 ease-in-out transform scale-0 opacity-0';
        document.body.appendChild(messageBox);
    }

    messageBox.textContent = message;
    // Reset class for transitions and apply base styles
    messageBox.className = 'fixed top-5 left-1/2 -translate-x-1/2 p-4 rounded-lg shadow-lg z-50 transition-all duration-300 ease-in-out';

    // Apply styling based on type
    switch (type) {
        case 'success':
            messageBox.style.backgroundColor = '#d4edda';
            messageBox.style.color = '#155724';
            messageBox.style.border = '1px solid #c3e6cb';
            break;
        case 'error':
            messageBox.style.backgroundColor = '#f8d7da';
            messageBox.style.color = '#721c24';
            messageBox.style.border = '1px solid #f5c6cb';
            break;
        case 'info':
        default:
            messageBox.style.backgroundColor = '#d1ecf1';
            messageBox.style.color = '#0c5460';
            messageBox.style.border = '1px solid #bee5eb';
            break;
    }

    // Show the message box
    messageBox.style.transform = 'translate(-50%, 0) scale(1)';
    messageBox.style.opacity = '1';

    // Hide the message box after 3 seconds
    setTimeout(() => {
        messageBox.style.transform = 'translate(-50%, 0) scale(0.9)';
        messageBox.style.opacity = '0';
        setTimeout(() => messageBox.remove(), 300); // Remove from DOM after transition
    }, 3000);
}
