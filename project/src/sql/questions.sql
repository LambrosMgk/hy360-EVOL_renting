-- For the question "Status of available or rented vehicles by category"
SELECT COUNT(*) AS car_count FROM cars WHERE vehicle_id IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Car')
SELECT COUNT(*) AS car_count FROM cars WHERE vehicle_id NOT IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Car')

SELECT COUNT(*) AS motorcycle_count FROM motorcycles WHERE vehicle_id IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Motorcycle')
SELECT COUNT(*) AS motorcycle_count FROM motorcycles WHERE vehicle_id NOT IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Motorcycle')

SELECT COUNT(*) AS scooter_count FROM scooters WHERE vehicle_id IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Scooter')
SELECT COUNT(*) AS scooter_count FROM scooters WHERE vehicle_id NOT IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Scooter')

SELECT COUNT(*) AS bicycle_count FROM bicycles WHERE vehicle_id IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Bicycle')
SELECT COUNT(*) AS bicycle_count FROM bicycles WHERE vehicle_id NOT IN (SELECT vehicle_id FROM rental_forms WHERE vehicle_type = 'Bicycle')

-- For the question "Most popular vehicle by category"
SELECT * FROM cars ORDER BY rent_counter DESC LIMIT 1
SELECT * FROM motorcycles ORDER BY rent_counter DESC LIMIT 1
SELECT * FROM scooters ORDER BY rent_counter DESC LIMIT 1
SELECT * FROM bicycles ORDER BY rent_counter DESC LIMIT 1