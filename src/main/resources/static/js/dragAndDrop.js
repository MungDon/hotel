$(function(){
	const dropZone = $("#drop_zone");
	const fileInput = $("#fileInput");
	const preview = $("#preview");
	const thumbnailInput = $(".thumbnail_input");
	const thumbnailPreview = $("#thumbnail_preview");

	dropZone.on("dragover", (event) => {
		event.preventDefault();
	});

	dropZone.on("drop", (event) => {
		event.preventDefault();
		const files = event.originalEvent.dataTransfer.files;
		if (files.length > 0) {
			displayImage(files,preview);
			fileInput[0].files = files;
		}
	});

	fileInput.on("change",(event) => {
		const roomFile = $(event.target)[0].files;
		displayImage(roomFile,preview);
	});

	dropZone.on("click", () => {
		fileInput[0].click();
	});

	thumbnailInput.on("change",(event) => {
		const thumbnailFile = $(event.target)[0].files;
		displayImage(thumbnailFile,thumbnailPreview);
	});

	const displayImage = (files,previewBox) => {
		console.log(files);
		previewBox.empty();
		$.each(files, (i, file) => {
			const reader = new FileReader();
			reader.onload = () => {
				const $imgElement = $("<img>").attr("src", reader.result)
					.css("display", "block")
					.addClass("preview-image");
				previewBox.append($imgElement);
			};
			reader.readAsDataURL(file);
		});
	};
});
