const initialize = () => {
    const button = document.querySelector("section.message-form button");
    const textBox = document.querySelector("section.message-form input");
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

const sendMessage = (textBox) => {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4 && xhr.status === 200) {
            const messages = document.querySelector("section.messages");
            const messageContent = escapeHtml(JSON.parse(xhr.response).text);
            const message = `<article class="message"><p>${messageContent}</p></article>`;
            messages.innerHTML += message;
            messages.scrollTop = messages.scrollHeight;
        }
    };
    const text = textBox.value;
    textBox.value = "";
    xhr.open("POST", `/messages?text=${text}`, true);
    xhr.send();
};

const updateMessages = () => {
    const xhr = new XMLHttpRequest();
    xhr.onreadystatechange = () => {
        if(xhr.readyState === 4 && xhr.status === 200) {
            const messages = document.querySelector("section.messages");
            const response = JSON.parse(xhr.response);
            messages.innerHTML = "";
            for(let i = 0; i < response.length; i++) {
                const text = escapeHtml(response[i].text);
                const message = `<article class="message"><p>${text}</p></article>`;
                messages.innerHTML += message;
            }
            messages.scrollTop = messages.scrollHeight;
        }
    };
    xhr.open("GET", "/messages", true);
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