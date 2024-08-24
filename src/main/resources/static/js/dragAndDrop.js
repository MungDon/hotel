$(function(){
	const dropZone = $("#drop_zone");
	const fileInput = $("#fileInput");
	const preview = $("#preview");

	dropZone.on("dragover", (event) => {
		event.preventDefault();
	});

	dropZone.on("drop", (event) => {
		event.preventDefault();
		const files = event.originalEvent.dataTransfer.files; // 'e'가 아니라 'event'
		if (files.length > 0) {
			displayImage(files);
			fileInput[0].files = files;
		}
	});

	dropZone.on("click", () => {
		fileInput[0].click();
	});

	const displayImage = (files) => {
		console.log(files);
		$.each(files, (i, file) => {
			const reader = new FileReader();
			reader.onload = () => {
				const $imgElement = $("<img>").attr("src", reader.result)
					.css("display", "block")
					.addClass("preview-image");
				preview.append($imgElement);
			};
			reader.readAsDataURL(file);
		});
	};
});
