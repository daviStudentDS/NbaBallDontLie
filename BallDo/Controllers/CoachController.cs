using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using BallDo.Data;
using BallDo.Models;
using Microsoft.EntityFrameworkCore;

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
        

        [HttpGet]
        public async Task<IEnumerable<Coach>> GetAllCoaches()
        {
            var coaches = _context.Coachies.Include(c => c.Team);
            return coaches;
        }

        [HttpGet("{id}")]
        public IActionResult GetCoachById(int id)
        {
            // Use Include para carregar as propriedades relacionadas, evitando ciclos de referência.
            var coach = _context.Coachies.Include(c => c.Team).FirstOrDefault(c => c.Id == id);
            if (coach == null)
                if (coach == null)
            {
                return NotFound();
            }

            return Ok(coach);
        }
        [HttpPost]
        public IActionResult CreateCoach(Coach coach)
        {
            _context.Coachies.Add(coach);
            _context.SaveChanges();
            return CreatedAtAction(nameof(GetCoachById), new { id = coach.Id }, coach);
        }

        [HttpPut("{id}")]
        public IActionResult UpdateCoach(int id, Coach updatedCoach)
        {
            var coach = _context.Coachies.FirstOrDefault(c => c.Id == id);
            if (coach == null)
            {
                return NotFound();
            }
            coach.Name = updatedCoach.Name;
            coach.ExperienceYears = updatedCoach.ExperienceYears;
            _context.SaveChanges();
            return NoContent();
        }

        [HttpDelete("{id}")]
        public IActionResult DeleteCoach(int id)
        {
            var coach = _context.Coachies.FirstOrDefault(c => c.Id == id);
            if (coach == null)
            {
                return NotFound();
            }
            _context.Coachies.Remove(coach);
            _context.SaveChanges();
            return NoContent();
        }
    }
}
