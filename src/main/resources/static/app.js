var webSocket;
function init() {
  webSocket = new WebSocket("ws://100.27.31.152:8080/calcstream");

  webSocket.onopen = () => {
    console.log("Connection established");
  };

  webSocket.onclose = () => {
    console.log("Connection closed");
  };

  webSocket.onmessage = (message) => {
    $("#chatstream").append("<div><strong>" + message.data + "</strong></div>");
    console.log(message);
  };
}

function submitForm() {
  $.post("calculate", $("#formInput").serialize());
}
