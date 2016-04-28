/**
 * 
 */

var lastResponse = {};
function handleSearchResults(jsonObj) {
	$("#search-result").empty();
	loadResults(jsonObj);
}

function loadResults(jsonObj) {
	lastResponse = jsonObj;
	$("#search-result-start").html("1");
	$("#search-result-end").html(lastResponse.end);
	$("#load-more-btn").remove();
	for (var i = 0; i < lastResponse.jobs.length; ++i) {
		var html = '<div class="search-result-job">'
				+ '<div class="search-result-job-title">' + '<a href="'
				+ lastResponse.jobs[i].url
				+ '">'
				+ lastResponse.jobs[i].jobTitle
				+ '</a>'
				+ '</div>'
				+ '<div class="search-result-job-employer-logo">'
				+ '<img src="'
				+ lastResponse.jobs[i].employer.logoUrl
				+ '" />'
				+ '</div>'
				+ '<div class="search-result-job-snippet">'
				+ lastResponse.jobs[i].snippet
				+ '</div>'
				+ '<div class="search-result-job-location">'
				+ lastResponse.jobs[i].formattedLocationFull
				+ '</div>'
				+ '<div class="search-result-job-relative-time">'
				+ lastResponse.jobs[i].formattedRelativeTime
				+ '</div>'
				+'<div class="search-result-job-employer-ratings col-lg-9">'
				+ '<div class="search-result-job-employer-careerOpportunitiesRating col-lg-2 ' + (parseInt(lastResponse.jobs[i].employer.careerOpportunitiesRating) < 3 ? 'red' : 'green') +'"> Career :'
				+ lastResponse.jobs[i].employer.careerOpportunitiesRating
				+ '</div>'
				+ '<div class="search-result-job-employer-compensationAndBenefitsRating col-lg-2 ' + (parseInt(lastResponse.jobs[i].employer.compensationAndBenefitsRating) < 3 ? 'red' : 'green') +'"> Compensation: '
				+ lastResponse.jobs[i].employer.compensationAndBenefitsRating
				+ '</div>'
				+ '<div class="search-result-job-employer-cultureAndValuesRating col-lg-2 ' + (parseInt(lastResponse.jobs[i].employer.cultureAndValuesRating) < 3 ? 'red' : 'green') +'"> Culture: '
				+ lastResponse.jobs[i].employer.cultureAndValuesRating
				+ '</div>'
				+ '<div class="search-result-job-employer-overallRating col-lg-2 ' + (parseInt(lastResponse.jobs[i].employer.overallRating) < 3 ? 'red' : 'green') +'"> Overall: '
				+ lastResponse.jobs[i].employer.overallRating
				+ '</div>'
				+ '<div class="search-result-job-employer-numberOfRatings col-lg-2 ' + (parseInt(lastResponse.jobs[i].employer.numberOfRatings) < 100 ? 'red' : 'green') +'">'
				+ '<i class="fa fa-comments" aria-hidden="true fa-fw"></i>&nbsp; ' 
				+ lastResponse.jobs[i].employer.numberOfRatings
				+ '</div>'
				+ '</div>'
				+ '</div>';
		$("#search-result").append(html);
		
		var html = '<button type="button" id="load-more-btn" class="btn btn-default search_jobs col-md-2 col-md-offset-5" onclick="loadMore()">Load More</button>';
		$("#search-result").append(html);
	}
}

function loadMore() {
	var req = "jobsearch/" + $("#search-query").val() + "/"
			+ $("#search-location").val() + "/" + lastResponse.end;
	$.ajax({
		url : req,
		success : function(result) {
			console.log(result);
			loadResults(result);
		}
	});
}