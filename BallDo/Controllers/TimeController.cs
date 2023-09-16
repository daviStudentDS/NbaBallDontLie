using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using BallDo.Data;
using BallDo.Models;
using System.ComponentModel.DataAnnotations;

namespace BallDo.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class TeamController : ControllerBase
    {
        private readonly ApplicationDbContext _context;

        public TeamController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet("{id}")]
        public IActionResult GetTeamById(int id)
        {
            var team = _context.Teams
                .Where(t => t.Id == id)
                .Select(t => new TeamDTO
                {
                    Id = t.Id,
                    Name = t.Name,
                    Coach = new CoachDTO
                    {
                        Id = t.Coach.Id,
                        Name = t.Coach.Name,
                        ExperienceYears = t.Coach.ExperienceYears,
                        // Outras propriedades do CoachDTO, se necessário
                    }
                })
                .FirstOrDefault();

            if (team == null)
            {
                return NotFound();
            }

            return Ok(team);
        }

        [HttpPost]
        public IActionResult CreateTeam(TeamDTO teamDTO)
        {
            if (ModelState.IsValid)
            {
                // Mapeie PlayersDTO para Players
                var players = teamDTO.Players?.Select(playerDTO => new Player
                {
                    // Mapeie as propriedades do PlayerDTO aqui.
                    // Exemplo: Name = playerDTO.Name,
                }).ToList();

                // Crie o objeto Team com as propriedades mapeadas
                var team = new Team
                {
                    Name = teamDTO.Name,
                    Players = (IEnumerable<Player>)teamDTO.Players,
                    
                };

                _context.Teams.Add(team);
                _context.SaveChanges();
                return CreatedAtAction(nameof(GetTeamById), new { id = team.Id }, team);
            }

            // Se o modelo não for válido, retorne um BadRequest com os erros de validação.
            return BadRequest(ModelState);
        }

        [HttpPut("{id}")]
        public IActionResult UpdateTeam(int id, Team updatedTeam)
        {
            var team = _context.Teams.FirstOrDefault(t => t.Id == id);
            if (team == null)
            {
                return NotFound();
            }

            team.Name = updatedTeam.Name;
            // Outras atualizações de propriedades, se necessário

            // Atualize o treinador associado à equipe
            team.Coach = updatedTeam.Coach;

            _context.SaveChanges();
            return NoContent();
        }

        [HttpDelete("{id}")]
        public IActionResult DeleteTeam(int id)
        {
            var team = _context.Teams.FirstOrDefault(t => t.Id == id);
            if (team == null)
            {
                return NotFound();
            }
            _context.Teams.Remove(team);
            _context.SaveChanges();
            return NoContent();
        }
    }
}
