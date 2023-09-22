using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using BallDo.Data;
using BallDo.Models;
using Microsoft.EntityFrameworkCore;
using System.ComponentModel.DataAnnotations;

namespace BallDo.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class CoachController : ControllerBase
    {
        private readonly ApplicationDbContext _context;

        public CoachController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet("{id}")]
        public IActionResult GetCoach(int id)
        {
            var coach = _context.Coaches.FirstOrDefault(c => c.Id == id);
            if (coach == null)
            {
                return NotFound();
            }

            var coachDTO = new CoachDTO
            {
                Id = coach.Id,
                Name = coach.Name,
                ExperienceYears = coach.ExperienceYears,
                TeamId = coach.TeamId
            };

            return Ok(coachDTO);
        }

        [HttpPost]
        public IActionResult CreateCoach(CoachDTO coachDTO)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var coach = new Coach
            {
                Name = coachDTO.Name,
                ExperienceYears = coachDTO.ExperienceYears,
                TeamId = coachDTO.TeamId
            };

            _context.Coaches.Add(coach);
            _context.SaveChanges();

            return CreatedAtAction(nameof(GetCoach), new { id = coach.Id }, coach);
        }

        [HttpPut("{id}")]
        public IActionResult UpdateCoach(int id, CoachDTO coachDTO)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var coach = _context.Coaches.FirstOrDefault(c => c.Id == id);
            if (coach == null)
            {
                return NotFound();
            }

            coach.Name = coachDTO.Name;
            coach.ExperienceYears = coachDTO.ExperienceYears;
            coach.TeamId = coachDTO.TeamId;

            _context.SaveChanges();

            return NoContent();
        }

        [HttpDelete("{id}")]
        public IActionResult DeleteCoach(int id)
        {
            var coach = _context.Coaches.FirstOrDefault(c => c.Id == id);
            if (coach == null)
            {
                return NotFound();
            }

            _context.Coaches.Remove(coach);
            _context.SaveChanges();

            return NoContent();
        }
    }
}