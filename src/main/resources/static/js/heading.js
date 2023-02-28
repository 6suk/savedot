function editProfile(id) {
	console.log(id)
	$.ajax({
		type: "GET",
        url: "/aside/profile/" + id,
        success: function(result) {
            const profile = JSON.parse(result);
            $('#filename').val(profile.filename);
        }
	});
	$('#profile').hide();
	$('#hiddenProfile').show();
}

function editExecute(id) {
	const formData = new FormData();
	formData.append('id', id);
	console.log("id: " + id);
	formData.append('filename', $('#filename').val());
	let imageInputVal = $('#image')[0];
	formData.append('image', imageInputVal.files[0]);
	$.ajax({
		type: "POST",
        url: "/aside/profile",
        data: formData,
		processData: false,
		contentType: false,
        success: function(result) {
            /* const profile = JSON.parse(result);
            console.log(profile); */
    		$('#profile').show();
    		$('#hiddenProfile').hide();
            location.reload();
        },
	});
}

function editCancel() {
	$('#profile').show();
	$('#hiddenProfile').hide();
}