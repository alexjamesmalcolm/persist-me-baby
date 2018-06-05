const initialize = () => {
	const button = document.querySelector("section.message-form button[type='submit']");
	const textBox = document.querySelector("section.message-form input[type='text']");
	const messages = [];
	button.addEventListener("click", () => {
		sendMessage(textBox, messages);
	});
	textBox.addEventListener("keyup", () => {
		if (event.keyCode === 13) {
			button.click();
		}
	});
	const $messageSection = new Vue({
		el: "#messages",
		data: {
			messages: messages
		}
	});
	updateMessages(messages);
	setInterval(() => {
		updateMessages(messages);
	}, 2000);
};

const sendMessage = (textBox, messages) => {
	const text = textBox.value;
	textBox.value = "";
	request(response => {
		messages.push(response);
	}, "POST", `/messages?text=${text}`);
};

const updateMessages = messages => {
	request(response => {
		messages.length = 0;
		for(let i = 0; i < response.length; i++) {
			messages.push(response[i]);
		}
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