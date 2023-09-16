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
                .Select(c => new CoachDTO
                {
                    Id = c.Id,
                    Name = c.Name,
                    ExperienceYears = c.ExperienceYears,
                    Team = new TeamDTO
                    {
                        Id = c.Team.Id,
                        Name = c.Team.Name,
                     
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

        [HttpPost("AddCoachToTeam/{coachId}/{teamId}")]
        public IActionResult AddCoachToTeam(int coachId, int teamId)
        {
            // Primeiro, verifique se o Coach e o Team existem no banco de dados
            var coach = _context.Coachies.FirstOrDefault(c => c.Id == coachId);
            var team = _context.Teams.FirstOrDefault(t => t.Id == teamId);

            if (coach == null || team == null)
            {
                return NotFound("Coach ou Team não encontrado");
            }

            // Associe o Coach ao Team
            coach.Team = team;

            // Salve as alterações no banco de dados
            _context.SaveChanges();

            return Ok($"Coach {coach.Name} afiliado ao Team {team.Name}");
        }

        [HttpPost("CreateCoachWithoutTeam")]
        public IActionResult CreateCoachWithoutTeam(Coach coach)
        {
            if (ModelState.IsValid)
            {
                // Adicione o Coach ao contexto do EF e salve no banco de dados
                _context.Coachies.Add(coach);
                _context.SaveChanges();

                // Retorne o Coach criado com seu ID
                return CreatedAtAction(nameof(GetCoachById), new { id = coach.Id }, coach);
            }

            // Se o modelo não for válido, retorne um BadRequest com os erros de validação.
            return BadRequest(ModelState);
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
