// Simple contact form validation
document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("contactForm");
  const messageEl = document.getElementById("formMessage");

  if (form) {
    form.addEventListener("submit", (e) => {
      e.preventDefault();

      const name = document.getElementById("name").value.trim();
      const email = document.getElementById("email").value.trim();
      const message = document.getElementById("message").value.trim();

      if (!name || !email || !message) {
        messageEl.textContent = "⚠ Please fill in all fields.";
        messageEl.style.color = "red";
      } else {
        messageEl.textContent = "✅ Thank you! Your message has been sent.";
        messageEl.style.color = "green";
        form.reset();
      }
    });
  }
});
