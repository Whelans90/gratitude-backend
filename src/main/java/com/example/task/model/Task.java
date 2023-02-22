package com.example.task.model;

import java.time.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "task")
public class Task {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="task_id")
            private Long id;
        
        @NotBlank(message = "Name is mandatory")
        @Column(name="name")
        private String name;
        
        @Column(name="date_created")
        private LocalDateTime dateCreated;
        
        @Column(name="date_completed")
        private LocalDateTime dateCompleted;
        
        @Column(name="description")
        private String description;
        
        @Column(name="owner_id")
        private Long ownerID;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public LocalDateTime getDateCreated() {
			return dateCreated;
		}

		public void setDateCreated(LocalDateTime dateCreated) {
			this.dateCreated = dateCreated;
		}

		public LocalDateTime getDateCompleted() {
			return dateCompleted;
		}

		public void setDateCompleted(LocalDateTime dateCompleted) {
			this.dateCompleted = dateCompleted;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Long getOwnerID() {
			return ownerID;
		}

		public void setOwnerID(Long ownerID) {
			this.ownerID = ownerID;
		}
}