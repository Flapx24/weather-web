document.addEventListener("DOMContentLoaded", () => {
    const weatherCard = document.querySelector(".weather-card");
    
    if (weatherCard) {
        applyWeatherEffects(weatherCard);
        
        animateCardEntrance(weatherCard);
        
        addCardInteractions(weatherCard);
    }
    
    initTooltips();
    
    registerServiceWorker();
});

function applyWeatherEffects(card) {
    const descriptionEl = card.querySelector(".text-capitalize");
    if (!descriptionEl) return;

    const description = descriptionEl.textContent.toLowerCase();
    const tempEl = card.querySelector(".temp-big");
    const temp = tempEl ? parseFloat(tempEl.textContent) : null;
    
    // Create overlay div
    const overlay = document.createElement("div");
    overlay.className = "weather-overlay";
    
    // Determine weather type
    if (description.includes("rain") || description.includes("lluvia")) {
        overlay.classList.add("rain-overlay");
        card.style.setProperty("--primary-color", "#3a86ff");
    } 
    else if (description.includes("cloud") || description.includes("nublado")) {
        overlay.classList.add("cloud-overlay");
        card.style.setProperty("--primary-color", "#6c757d");
    } 
    else if (description.includes("sun") || description.includes("clear") || description.includes("soleado")) {
        overlay.classList.add("sun-overlay");
        card.style.setProperty("--primary-color", "#ff6d00");
    }
    
    // Adjust colors based on temperature if available
    if (temp !== null) {
        if (temp < 5) {
            card.style.setProperty("--primary-color", "#4cc9f0");
            card.style.setProperty("--secondary-color", "#4895ef");
        } else if (temp > 30) {
            card.style.setProperty("--primary-color", "#f72585");
            card.style.setProperty("--secondary-color", "#b5179e");
        }
    }
    
    card.appendChild(overlay);
}

function animateCardEntrance(card) {
    card.style.opacity = "0";
    card.style.transform = "translateY(30px) scale(0.98)";
    card.style.transition = "none";
    
    void card.offsetWidth;
    
    card.style.transition = "all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.1), background-color 0.3s ease";
    card.style.opacity = "1";
    card.style.transform = "translateY(0) scale(1)";
    
    setTimeout(() => {
        card.style.transition = "";
    }, 600);
}

function addCardInteractions(card) {
    card.addEventListener("mouseenter", () => {
        card.style.transform = "translateY(-5px)";
        card.style.boxShadow = "0 15px 35px rgba(0, 0, 0, 0.2)";
    });
    
    card.addEventListener("mouseleave", () => {
        card.style.transform = "translateY(0)";
        card.style.boxShadow = "0 10px 30px rgba(0, 0, 0, 0.15)";
    });
    
    // Click effect
    card.addEventListener("click", () => {
        card.style.transform = "scale(0.98)";
        setTimeout(() => {
            card.style.transform = "scale(1)";
        }, 200);
    });
}

function initTooltips() {
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(tooltipTriggerEl => {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
}

function registerServiceWorker() {
    if ('serviceWorker' in navigator) {
        window.addEventListener('load', () => {
            navigator.serviceWorker.register('/sw.js')
                .then(registration => {
                    console.log('ServiceWorker registered with scope:', registration.scope);
                })
                .catch(error => {
                    console.log('ServiceWorker registration failed:', error);
                });
        });
    }
}

// Utility function for smooth scroll
function smoothScrollTo(element) {
    if (element) {
        element.scrollIntoView({
            behavior: 'smooth',
            block: 'start'
        });
    }
}