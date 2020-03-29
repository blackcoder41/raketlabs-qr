(function ($) {
    $(function () {
        
        var qrcode = new QRCode($("#qr-png")[0], {
            text: $("#qr-data").text(),
            width: 256,
            height: 256,
            colorDark: "#000000",
            colorLight: "#ffffff",
            correctLevel: QRCode.CorrectLevel.H
        });
        
        var qrcode = new QRCode($("#qr-svg")[0], {
            text: $("#qr-data").text(),
            width: 256,
            height: 256,
            colorDark: "#000000",
            colorLight: "#ffffff",
            correctLevel: QRCode.CorrectLevel.H,
            useSVG: true
        });
        

        $("#download").click(function() {
        	$("#qr-form").submit();
        });

        function downloadQRSVG () {
            var content = $("#qr-svg-container").html();
            content = content.replace(/NS\d+:href/g, 'xlink:href'); // Safari patch
            
            var blob = new Blob([content], { type: 'image/svg+xml' });
            
            downloadBlob(blob, "qr.svg");
        }
        
        function downloadQRPNG () {
            var content = $("#qr-png-container img")[0].src;
            downloadBase64(content, "qr.png");
        }

        function downloadBlob (blob, fileName) {
            url = window.URL.createObjectURL(blob);
            var a = document.createElement("a");
            document.body.appendChild(a);
            a.style = "display: none";
            a.href = url;
            a.download = fileName;
            a.target = "_blank"
            a.click();
            //window.URL.revokeObjectURL(url);
        };
        
        function downloadBase64 (data, fileName) {
            url = data
            var a = document.createElement("a");
            document.body.appendChild(a);
            a.style = "display: none";
            a.href = url;
            a.download = fileName;
            a.target = "_blank"
            a.click();
            //window.URL.revokeObjectURL(url);
        };
    });
})(jQuery);