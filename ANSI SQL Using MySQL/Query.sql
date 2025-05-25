-- 1. User Upcoming Events
SELECT
    U.full_name AS user_name,
    E.title AS event_title,
    E.city AS event_city,
    E.start_date
FROM Users AS U
JOIN Registrations AS R ON U.user_id = R.user_id
JOIN Events AS E ON R.event_id = E.event_id
WHERE
    E.status = 'upcoming' AND
    E.city = U.city AND -- Event is in the user's city
    E.start_date > NOW() -- Event starts in the future (adjust based on current date)
ORDER BY E.start_date;

-- 2.Top Rated Events
SELECT
    E.title AS event_title,
    AVG(F.rating) AS average_rating,
    COUNT(F.feedback_id) AS total_feedback_submissions
FROM Events AS E
JOIN Feedback AS F ON E.event_id = F.event_id
GROUP BY E.title
HAVING COUNT(F.feedback_id) >= 10 -- Condition for at least 10 feedback submissions
ORDER BY average_rating DESC
LIMIT 1; -- To get only the top event
-- 3 Inactive Users

SELECT
    U.user_id,
    U.full_name,
    U.email
FROM Users AS U
WHERE U.user_id NOT IN (
    SELECT DISTINCT R.user_id
    FROM Registrations AS R
    WHERE R.registration_date >= DATE_SUB(CURDATE(), INTERVAL 90 DAY)
);
-- 4  Peak Session Hours
SELECT
    E.title AS event_title,
    COUNT(S.session_id) AS sessions_between_10_and_12
FROM Events AS E
JOIN Sessions AS S ON E.event_id = S.event_id
WHERE
    TIME(S.start_time) >= '10:00:00' AND
    TIME(S.start_time) < '12:00:00'
GROUP BY E.title
ORDER BY sessions_between_10_and_12 DESC;
-- 5 Most Active Cities
SELECT
    U.city,
    COUNT(DISTINCT R.user_id) AS distinct_registered_users
FROM Users AS U
JOIN Registrations AS R ON U.user_id = R.user_id
GROUP BY U.city
ORDER BY distinct_registered_users DESC
LIMIT 5;
-- 6 Event Resource Summary
SELECT
    E.title AS event_title,
    SUM(CASE WHEN R.resource_type = 'pdf' THEN 1 ELSE 0 END) AS num_pdfs,
    SUM(CASE WHEN R.resource_type = 'image' THEN 1 ELSE 0 END) AS num_images,
    SUM(CASE WHEN R.resource_type = 'link' THEN 1 ELSE 0 END) AS num_links,
    COUNT(R.resource_id) AS total_resources
FROM Events AS E
LEFT JOIN Resources AS R ON E.event_id = R.event_id
GROUP BY E.title
ORDER BY E.title;
-- 7 Low Feedback Alerts
SELECT
    U.full_name AS user_name,
    U.email,
    E.title AS event_title,
    F.rating,
    F.comments
FROM Feedback AS F
JOIN Users AS U ON F.user_id = U.user_id
JOIN Events AS E ON F.event_id = E.event_id
WHERE F.rating < 3;
-- 8 Sessions per Upcoming Event
SELECT
    E.title AS event_title,
    E.status,
    COUNT(S.session_id) AS number_of_sessions
FROM Events AS E
LEFT JOIN Sessions AS S ON E.event_id = S.event_id
WHERE E.status = 'upcoming'
GROUP BY E.title, E.status
ORDER BY E.title;
-- 9  Organizer Event Summary
SELECT
    U.full_name AS organizer_name,
    E.status AS event_status,
    COUNT(E.event_id) AS number_of_events
FROM Users AS U
JOIN Events AS E ON U.user_id = E.organizer_id
GROUP BY U.full_name, E.status
ORDER BY U.full_name, E.status;
-- 10 Feedback Gap
SELECT E.title AS event_title
FROM Events AS E
JOIN Registrations AS R ON E.event_id = R.event_id -- Ensure it has registrations
LEFT JOIN Feedback AS F ON E.event_id = F.event_id
WHERE F.feedback_id IS NULL
GROUP BY E.title; -- Group by title to get distinct events
-- 11 Daily New User Count
SELECT
    registration_date,
    COUNT(user_id) AS daily_new_users
FROM Users
WHERE registration_date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)
GROUP BY registration_date
ORDER BY registration_date;
-- 12 Event with Maximum Sessions

SELECT
    E.title AS event_title,
    COUNT(S.session_id) AS number_of_sessions
FROM Events AS E
JOIN Sessions AS S ON E.event_id = S.event_id
GROUP BY E.title
ORDER BY number_of_sessions DESC
LIMIT 1;
-- 13 Average Rating per City
SELECT
    E.city,
    AVG(F.rating) AS average_rating
FROM Events AS E
JOIN Feedback AS F ON E.event_id = F.event_id
GROUP BY E.city
ORDER BY average_rating DESC;
-- 14 Most Registered Events
SELECT
    E.title AS event_title,
    COUNT(R.registration_id) AS total_registrations
FROM Events AS E
JOIN Registrations AS R ON E.event_id = R.event_id
GROUP BY E.title
ORDER BY total_registrations DESC
LIMIT 3;
-- 15 Event Session Time Conflict
SELECT
    E.title AS event_title,
    S1.title AS session1_title,
    S1.speaker_name AS session1_speaker,
    S1.start_time AS session1_start,
    S1.end_time AS session1_end,
    S2.title AS session2_title,
    S2.speaker_name AS session2_speaker,
    S2.start_time AS session2_start,
    S2.end_time AS session2_end
FROM Sessions AS S1
JOIN Sessions AS S2 ON S1.event_id = S2.event_id AND S1.session_id < S2.session_id
JOIN Events AS E ON S1.event_id = E.event_id
WHERE
    (S1.start_time < S2.end_time AND S1.end_time > S2.start_time);

--  16 Unregistered Active Users
SELECT
    U.user_id,
    U.full_name,
    U.email,
    U.registration_date
FROM Users AS U
LEFT JOIN Registrations AS R ON U.user_id = R.user_id
WHERE
    U.registration_date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY) AND
    R.registration_id IS NULL;
    
-- 17 Multi-Session Speakers
SELECT
    speaker_name,
    COUNT(session_id) AS number_of_sessions
FROM Sessions
GROUP BY speaker_name
HAVING COUNT(session_id) > 1
ORDER BY number_of_sessions DESC;
-- 18 Resource Availability Check
SELECT E.title AS event_title
FROM Events AS E
LEFT JOIN Resources AS R ON E.event_id = R.event_id
WHERE R.resource_id IS NULL;
-- 19 Completed Events with Feedback Summary
SELECT
    E.title AS event_title,
    COUNT(DISTINCT R.registration_id) AS total_registrations,
    AVG(F.rating) AS average_feedback_rating
FROM Events AS E
LEFT JOIN Registrations AS R ON E.event_id = R.event_id
LEFT JOIN Feedback AS F ON E.event_id = F.event_id
WHERE E.status = 'completed'
GROUP BY E.title
ORDER BY E.title;

-- 20 User Engagement Index
SELECT
    U.full_name AS user_name,
    COUNT(DISTINCT R.event_id) AS events_registered_for,
    COUNT(DISTINCT F.feedback_id) AS feedback_submitted
FROM Users AS U
LEFT JOIN Registrations AS R ON U.user_id = R.user_id
LEFT JOIN Feedback AS F ON U.user_id = F.user_id
GROUP BY U.user_id, U.full_name
ORDER BY events_registered_for DESC, feedback_submitted DESC;
-- 21 Top Feedback Providers
SELECT
    U.full_name AS user_name,
    U.email,
    COUNT(F.feedback_id) AS total_feedback_entries
FROM Users AS U
JOIN Feedback AS F ON U.user_id = F.user_id
GROUP BY U.user_id, U.full_name, U.email
ORDER BY total_feedback_entries DESC
LIMIT 5;
-- 22 Duplicate Registrations Check
SELECT
    user_id,
    event_id,
    COUNT(registration_id) AS duplicate_count
FROM Registrations
GROUP BY user_id, event_id
HAVING COUNT(registration_id) > 1;
-- 23 Registration Trends
SELECT
    DATE_FORMAT(registration_date, '%Y-%m') AS registration_month,
    COUNT(registration_id) AS monthly_registrations
FROM Registrations
WHERE registration_date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
GROUP BY registration_month
ORDER BY registration_month;
-- 24 Average Session Duration per Event
SELECT
    E.title AS event_title,
    AVG(TIMESTAMPDIFF(MINUTE, S.start_time, S.end_time)) AS average_session_duration_minutes
FROM Events AS E
JOIN Sessions AS S ON E.event_id = S.event_id
GROUP BY E.title
ORDER BY average_session_duration_minutes DESC;
-- 25 Events Without Sessions
SELECT E.title AS event_title
FROM Events AS E
LEFT JOIN Sessions AS S ON E.event_id = S.event_id
WHERE S.session_id IS NULL;
    