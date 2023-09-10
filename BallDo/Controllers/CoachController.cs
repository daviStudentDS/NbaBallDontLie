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
        public IActionResult GetAllCoaches()
        {
            var coaches = _context.Coachies
                .Select(c => new
                {
                    c.Id,
                    c.Name,
                    c.ExperienceYears,
                    Team = new
                    {
                        c.Team.Id,
                        c.Team.Name,
                        // Adicione outras propriedades da equipe, se necessário
                    }
                })
                .ToList();

            return Ok(coaches);
        }

        [HttpGet("{id}")]
        public IActionResult GetCoachById(int id)
        {
            var coach = _context.Coachies
                .Where(c => c.Id == id)
                .Select(c => new
                {
                    c.Id,
                    c.Name,
                    c.ExperienceYears,
                    Team = new
                    {
                        c.Team.Id,
                        c.Team.Name,
                        // Adicione outras propriedades da equipe, se necessário
                    }
                })
                .FirstOrDefault();

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
            // Atualize outras propriedades conforme necessário
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
