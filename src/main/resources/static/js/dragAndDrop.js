	const dropZone = document.getElementById("drop_zone");
	const input = document.getElementById("input");
	const preview = document.getElementById("preview");

	dropZone.addEventListener("dragover", (e) => {
		e.preventDefault();
	});

	dropZone.addEventListener("drop", (e) => {
	    e.preventDefault();

	    const files = e.dataTransfer.files;
	    if (files.length > 0) {
	    	displayImage(files);
	    	input.files = files;
	    }
	 });

	 dropZone.addEventListener("click", () => {
	    input.click();
	 });

	 input.addEventListener("change", () => {
	    const files = input.files;
	    if (files.length > 0) {
	       displayImage(files);
	    }
	 });

	    function displayImage(files) {
	    	console.log(files)
	    	 for (let i = 0; i < files.length; i++) {
	    		 const file = files[i];
	        	const reader = new FileReader();
	        	reader.onload = () => {
	        		const imgElement = document.createElement("img");
	        		imgElement.src = reader.result;
	        		imgElement.style.display = "block";
	            	imgElement.classList.add("preview-image");
	            	preview.appendChild(imgElement);
	       	 	};
	        	reader.readAsDataURL(file);
	    	};
	    };