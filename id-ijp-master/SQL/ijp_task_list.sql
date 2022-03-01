CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `ijp_task_list` AS
    SELECT 
        `can`.`ijp_recr_id` AS `ijp_recr_id`,
        `can`.`ijp_trx_no` AS `ijp_trx_no`,
        `trx`.`pending_with` AS `pending_with`,
        (SELECT 
                `u`.`fullname`
            FROM
                (`tt_activity_log` `log`
                LEFT JOIN `tm_user` `u` ON ((`log`.`activity_owner` = `u`.`emp_code`)))
            WHERE
                ((CONVERT( `log`.`trx_number` USING UTF8MB4) = `can`.`ijp_trx_no`)
                    AND (`log`.`old_status_code` = 'PENDING_WITH_RM'))) AS `emp_rm_name`,
        (SELECT 
                `log`.`remarks`
            FROM
                `tt_activity_log` `log`
            WHERE
                ((CONVERT( `log`.`trx_number` USING UTF8MB4) = `can`.`ijp_trx_no`)
                    AND (`log`.`old_status_code` = 'PENDING_WITH_RM'))) AS `emp_rm_remark`,
        (SELECT 
                `log`.`out_come`
            FROM
                `tt_activity_log` `log`
            WHERE
                ((CONVERT( `log`.`trx_number` USING UTF8MB4) = `can`.`ijp_trx_no`)
                    AND (`log`.`old_status_code` = 'PENDING_WITH_RM'))) AS `emp_rm_status`,
        (SELECT 
                `log`.`activity_start_date`
            FROM
                `tt_activity_log` `log`
            WHERE
                ((CONVERT( `log`.`trx_number` USING UTF8MB4) = CONVERT( `can`.`ijp_trx_no` USING UTF8MB4))
                    AND (`log`.`old_status_code` = 'PENDING_WITH_RM'))) AS `emp_rm_approve_date`,
        (SELECT 
                `u`.`fullname`
            FROM
                (`tt_activity_log` `log`
                LEFT JOIN `tm_user` `u` ON ((`log`.`activity_owner` = `u`.`emp_code`)))
            WHERE
                ((CONVERT( `log`.`trx_number` USING UTF8MB4) = `can`.`ijp_trx_no`)
                    AND (`log`.`old_status_code` = 'PENDING_WITH_IJP_APPROVER'))) AS `ijp_approver_name`,
        (SELECT 
                `log`.`remarks`
            FROM
                `tt_activity_log` `log`
            WHERE
                ((CONVERT( `log`.`trx_number` USING UTF8MB4) = `can`.`ijp_trx_no`)
                    AND (`log`.`old_status_code` = 'PENDING_WITH_IJP_APPROVER'))) AS `ijp_approver_remark`,
        (SELECT 
                `log`.`out_come`
            FROM
                `tt_activity_log` `log`
            WHERE
                ((CONVERT( `log`.`trx_number` USING UTF8MB4) = `can`.`ijp_trx_no`)
                    AND (`log`.`old_status_code` = 'PENDING_WITH_IJP_APPROVER'))) AS `ijp_approver_status`,
        (SELECT 
                `log`.`activity_start_date`
            FROM
                `tt_activity_log` `log`
            WHERE
                ((CONVERT( `log`.`trx_number` USING UTF8MB4) = CONVERT( `can`.`ijp_trx_no` USING UTF8MB4))
                    AND (`log`.`old_status_code` = 'PENDING_WITH_IJP_APPROVER'))) AS `ijp_approver_date`,
        (SELECT 
                `u`.`fullname`
            FROM
                (`tt_activity_log` `log`
                LEFT JOIN `tm_user` `u` ON ((`log`.`activity_owner` = `u`.`emp_code`)))
            WHERE
                ((CONVERT( `log`.`trx_number` USING UTF8MB4) = `can`.`ijp_trx_no`)
                    AND (`log`.`old_status_code` = 'PENDING_WITH_TALENT_HR'))) AS `ijp_talent_hr_name`,
        (SELECT 
                `log`.`remarks`
            FROM
                `tt_activity_log` `log`
            WHERE
                ((CONVERT( `log`.`trx_number` USING UTF8MB4) = `can`.`ijp_trx_no`)
                    AND (`log`.`old_status_code` = 'PENDING_WITH_TALENT_HR'))) AS `ijp_talent_hr_remark`,
        (SELECT 
                `log`.`out_come`
            FROM
                `tt_activity_log` `log`
            WHERE
                ((CONVERT( `log`.`trx_number` USING UTF8MB4) = `can`.`ijp_trx_no`)
                    AND (`log`.`old_status_code` = 'PENDING_WITH_TALENT_HR'))) AS `ijp_talent_hr_status`,
        `job`.`jbd_title` AS `jbd_title`,
        `job`.`jbd_desig_code` AS `jbd_desig_code`,
        `job`.`jbd_desig_name` AS `jbd_desig_name`,
        `job`.`jbd_dept_code` AS `jbd_dept_code`,
        `job`.`jbd_dept_name` AS `jbd_dept_name`,
        `job`.`jbd_loc_code` AS `jbd_loc_code`,
        `job`.`jbd_loc_name` AS `jbd_loc_name`,
        `job`.`jbd_year_of_exp` AS `jbd_yo_exp`,
        `can`.`ijp_total_exp` AS `ijp_total_exp`,
        `can`.`ijp_current_role_since` AS `ijp_current_role_since`,
        `can`.`ijp_resume_file_name` AS `ijp_resume_file_name`,
        `can`.`created_on` AS `ijp_created_on`,
        `user`.`fullname` AS `fullname`,
        `user`.`emp_code` AS `EMP_CODE`,
        `user`.`contact_no` AS `CONTACT_NO`,
        `user`.`official_email_id` AS `OFFICIAL_EMAIL_ID`,
        `dsg`.`dsg_name` AS `emp_current_desig_name`,
        `dep`.`dpt_name` AS `emp_current_dept_name`,
        `city`.`city_name` AS `emp_current_loc_name`,
        `trx`.`status_code` AS `current_status_code`,
        `trx`.`status_name` AS `current_status_name`
    FROM
        (((`tt_transaction` `trx`
        LEFT JOIN `tt_ijp_job_recruitment` `can` ON ((CONVERT( `trx`.`trx_number` USING UTF8MB4) = `can`.`ijp_trx_no`)))
        LEFT JOIN `tt_ijp_job_desc` `job` ON ((`can`.`jrt_job_id` = `job`.`jbd_id`)))
        LEFT JOIN `tm_user` `user` ON ((`can`.`created_by` = CONVERT( `user`.`emp_code` USING UTF8MB4)))
        LEFT JOIN `tm_department` `dep` ON `user`.`dpt_code` = `dep`.`dpt_code` 
        LEFT JOIN `tm_designation` `dsg` ON `user`.`dsg_code` = `dsg`.`dsg_code`
        LEFT JOIN `tm_city` `city` ON `user`.`city` = `city`.`city_code` 
    WHERE
        (`trx`.`trx_number` LIKE 'IJP%')
    ORDER BY `trx`.`updated_on`