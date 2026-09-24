document.addEventListener("DOMContentLoaded", () => {
	document.querySelectorAll(".login-password-toggle").forEach((button) => {
		const container = button.closest(".login-control");
		const input = container?.querySelector("input");

		if (!input) return;

		button.addEventListener("click", () => {
			const visible = input.type === "text";
			input.type = visible ? "password" : "text";
			button.setAttribute("aria-pressed", String(!visible));
			button.setAttribute(
				"aria-label",
				visible ? "Mostrar senha" : "Ocultar senha"
			);
			input.focus();
		});
	});
});
