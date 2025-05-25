document.addEventListener('DOMContentLoaded', () => {
            // Get DOM elements
            const phoneField = document.getElementById('phone');
            const eventTypeSelect = document.getElementById('eventType');
            const eventRegistrationForm = document.getElementById('eventRegistrationForm');
            const messageTextarea = document.getElementById('message'); // For event registration message
            const feedbackMessageTextarea = document.getElementById('feedbackMessage'); // For feedback message
            const imageGalleryImages = document.querySelectorAll('.image-border'); // Select images within gallery items
            const promoVideo = document.getElementById('promoVideo');
            const preferredEventTypeSelect = document.getElementById('preferredEventType');
            const clearPreferencesButton = document.getElementById('clearPreferencesButton');
            const findNearbyEventsButton = document.getElementById('findNearbyEventsButton');
            const videoStatusElement = document.getElementById('videoStatus'); // Added for video status
            const geolocationOutput = document.getElementById('geolocationOutput'); // Get the new dedicated output div

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
            if (promoVideo && videoStatusElement) {
                promoVideo.addEventListener('canplay', () => {
                    videoStatusElement.textContent = "Video ready to play";
                    videoStatusElement.className = 'message-info'; // Apply info styling
                });
                promoVideo.addEventListener('play', () => {
                    videoStatusElement.textContent = 'Video is playing...';
                    videoStatusElement.className = 'message-info';
                });

                promoVideo.addEventListener('pause', () => {
                    videoStatusElement.textContent = 'Video is paused.';
                    videoStatusElement.className = 'message-info';
                });

                promoVideo.addEventListener('ended', () => {
                    videoStatusElement.textContent = 'Video has ended. Thank you for watching!';
                    videoStatusElement.className = 'message-success';
                });

                promoVideo.addEventListener('error', () => {
                    videoStatusElement.textContent = 'Error loading video.';
                    videoStatusElement.className = 'message-error';
                });
            } else {
                console.error("Elements for video status not found.");
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
            } else {
                console.error("Element for preferred event type select not found.");
            }

            // Add a "Clear Preferences" button that clears both localStorage and sessionStorage
            if (clearPreferencesButton) {
                clearPreferencesButton.addEventListener('click', clearPreferences);
            } else {
                console.error("Element for clear preferences button not found.");
            }

            // --- Task 9: Geolocation for Event Mapping ---

            // Create a button "Find Nearby Events" and attach click listener
            if (findNearbyEventsButton) {
                findNearbyEventsButton.addEventListener('click', findNearbyEvents);
            } else {
                console.error("Element for find nearby events button not found.");
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

            if (!phoneField || !phoneError) {
                console.error("Phone field or error element not found for validation.");
                return true; // Prevent further errors if elements are missing
            }

            if (phoneField.value.trim() === '') {
                phoneError.textContent = ""; // Clear error if field is empty
                phoneField.classList.remove('border-red-500', 'focus:border-red-500', 'focus:ring-red-500');
                phoneField.classList.add('border-gray-300', 'focus:border-blue-500', 'focus:ring-blue-500');
                return true;
            }

            if (!phonePattern.test(phoneField.value)) {
                phoneError.textContent = "Please enter a valid phone number (e.g., 123-456-7890 or 1234567890).";
                phoneField.classList.add('border-red-500', 'focus:border-red-500', 'focus:ring-red-500');
                phoneField.classList.remove('border-gray-300', 'focus:border-blue-500', 'focus:ring-blue-500');
                return false;
            } else {
                phoneError.textContent = "";
                phoneField.classList.remove('border-red-500', 'focus:border-red-500', 'focus:ring-red-500');
                phoneField.classList.add('border-gray-300', 'focus:border-blue-500', 'focus:ring-blue-500');
                return true;
            }
        }

        /**
         * Displays the event fee based on the selected event type.
         */
        function displayEventFee() {
            const selectElement = document.getElementById('eventType');
            const eventFeeDisplay = document.getElementById('eventFee');
            if (!selectElement || !eventFeeDisplay) {
                console.error("Event type select or fee display element not found.");
                return;
            }
            const selectedOption = selectElement.options[selectElement.selectedIndex];
            const fee = selectedOption.getAttribute('data-fee') || '0'; // Default to 0 if no data-fee
            eventFeeDisplay.textContent = `Event Fee: $${fee}`;
        }

        /**
         * Displays a confirmation message after form submission.
         */
        function showConfirmation() {
            const confirmationOutput = document.getElementById('confirmationOutput');
            if (!confirmationOutput) {
                console.error("Confirmation output element not found.");
                return;
            }

            // Ensure phone number is valid before showing confirmation
            if (!validatePhoneNumber()) {
                confirmationOutput.textContent = "Please correct the phone number error before submitting.";
                confirmationOutput.className = 'message-error'; // Apply error styling
                return;
            }

            const name = document.getElementById('name')?.value;
            const email = document.getElementById('email')?.value;
            const eventTypeSelect = document.getElementById('eventType');
            const eventType = eventTypeSelect ? eventTypeSelect.value : '';

            if (name && email && eventType) {
                confirmationOutput.textContent = `Thank you, ${name}! Your registration for the ${eventType} event has been submitted. A confirmation email will be sent to ${email}.`;
                confirmationOutput.className = 'message-success'; // Apply success styling
            } else {
                confirmationOutput.textContent = "Please fill in all required fields.";
                confirmationOutput.className = 'message-error'; // Apply error styling
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
            const geolocationOutput = document.getElementById('geolocationOutput'); // Get the output div

            if (!geolocationOutput) {
                console.error("Geolocation output element not found.");
                displayCustomMessage('Error: Geolocation output area not found.', 'error');
                return;
            }

            // Clear previous output and set initial message and styling
            geolocationOutput.textContent = 'Attempting to find your location...';
            geolocationOutput.className = 'message-info';

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
                        geolocationOutput.textContent = `Found your location: Latitude ${lat}, Longitude ${lon}.`;
                        geolocationOutput.className = 'message-success'; // Apply success styling
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
                        geolocationOutput.textContent = `Error: ${errorMessage}`;
                        geolocationOutput.className = 'message-error'; // Apply error styling
                        console.error(`Geolocation error: ${errorMessage}`);
                    },
                    options
                );
            } else {
                geolocationOutput.textContent = 'Geolocation is not supported by your browser. Please update your browser.';
                geolocationOutput.className = 'message-error'; // Apply error styling
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
   
