const initialize = () => {
	const button = document.querySelector("section.message-form button[type='submit']");
	const textBox = document.querySelector("section.message-form input[type='text']");
	button.addEventListener("click", () => {
		sendMessage(textBox);
	});
	textBox.addEventListener("keyup", () => {
		if (event.keyCode === 13) {
			button.click();
		}
	});
	setInterval(() => {
		updateMessages();
	}, 2000);
};

const sendMessage = textBox => {
	const text = textBox.value;
	textBox.value = "";
	request((response) => {
		const messages = document.querySelector("section.messages");
		const messageContent = escapeHtml(response.text);
		const message = `<article class="message"><p>${messageContent}</p></article>`;
		messages.innerHTML += message;
		messages.scrollTop = messages.scrollHeight;
	}, "POST", `/messages?text=${text}`);
};

const updateMessages = () => {
	request(response => {
		const messages = document.querySelector("section.messages");
		messages.innerHTML = "";
		for (let i = 0; i < response.length; i++) {
			const text = escapeHtml(response[i].text);
			const message = `<article class="message"><p>${text}</p></article>`;
			messages.innerHTML += message;
		}
		messages.scrollTop = messages.scrollHeight;
	}, "GET", "/messages");
};

const getUserInfo = () => {
    let result;
    request(response => {
        console.log(response);
        result = response;
    }, "GET", "https://www.googleapis.com/userinfo/v2/me");
    return result;
};

const request = (callback, method, url) => {
	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		if (xhr.readyState === 4 && xhr.status === 200) {
			const response = JSON.parse(xhr.response);
			callback(response);
		}
	};
	xhr.open(method, url, true);
	const token = getMetaContent("name", "_csrf");
	const header = getMetaContent("name", "_csrf_header");
	xhr.setRequestHeader(header, token);
	xhr.send();
};

const escapeHtml = unsafe => {
	return unsafe
		.replace(/&/g, "&amp;")
		.replace(/</g, "&lt;")
		.replace(/>/g, "&gt;")
		.replace(/"/g, "&quot;")
		.replace(/'/g, "&#039;");
};

const getMetaContent = (property, name) => document.head.querySelector("[" + property + "=" + name + "]").content;